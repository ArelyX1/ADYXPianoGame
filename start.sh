#!/bin/bash

# Clean bin directory
echo "Cleaning bin directory..."
rm -rf bin/*

# Copy resource files to bin directory
echo "Copying resources..."
cp -r src/images bin/
cp -r src/music bin/

# Compile all Java source files
echo "Compiling Java files..."
javac -d bin -cp "lib/*" src/dynamic_beat_17/**/*.java src/dynamic_beat_17/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Starting game..."
    # Run the game
    java -cp "bin:lib/*" dynamic_beat_17.Main
else
    echo "Compilation failed!"
    exit 1
fi
