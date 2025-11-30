#!/bin/bash
set -e

export DISPLAY=:1

mkdir -p /tmp/.X11-unix

echo "Starting Xvfb on :1"
Xvfb :1 -screen 0 1280x720x24 &>/var/log/xvfb.log &
sleep 1

echo "Starting fluxbox"
fluxbox &>/var/log/fluxbox.log &
sleep 1

echo "Starting x11vnc (no password, for testing only)"
# WARNING: No password is set. For production set a password with -rfbauth.
x11vnc -display :1 -nopw -forever -shared -rfbport 5900 &>/var/log/x11vnc.log &
sleep 1

echo "Starting websockify/noVNC proxy on 6080"
cd /opt/websockify || exit 1
./run 6080 localhost:5900 &>/var/log/websockify.log &

echo "Serving noVNC web client"
cd /opt/noVNC || exit 1
# The vnc.html path depends on noVNC version; provide index.html fallback
sleep 1

echo "Starting Java application"
java -jar /app/dist.jar
