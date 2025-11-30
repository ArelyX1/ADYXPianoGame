#!/bin/sh
set -e

echo "Cleaning..."
rm -rf out dist.jar manifest.txt
mkdir -p out

echo "Compiling Java sources..."
JFILES=$(find src -name "*.java")
javac -d out $JFILES

echo "Creating JAR..."
printf "Main-Class: dynamic_beat_17.Main\n" > manifest.txt
jar cfm app.jar manifest.txt -C out .

if ls lib/*.jar 1> /dev/null 2>&1; then
	echo "Found libraries in lib/, unpacking into JAR..."
	for dep in lib/*.jar; do
		TMPDIR=$(mktemp -d)
		(cd "$TMPDIR" && jar xf "../$dep")
		jar uf app.jar -C "$TMPDIR" .
		rm -rf "$TMPDIR"
	done
fi

echo "Adding resources to JAR..."
jar uf app.jar -C src images -C src music || true

mv app.jar dist.jar
rm -f manifest.txt
echo "Built dist.jar"
