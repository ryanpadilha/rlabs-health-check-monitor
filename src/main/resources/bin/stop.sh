#!/bin/bash
#
# Stop Vulcano Health Check Monitor

echo "stop vulcano-health-check-monitor application..."

PID=$(cat /var/run/vulcano-health-check-monitor.pid)
kill -9 $PID
