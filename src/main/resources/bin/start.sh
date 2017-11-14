#!/bin/bash
#
# Start Vulcano Health Check Monitor Application

if [ -z $1 ]; then
  ENV="dev";
else
  ENV=$1
fi

APPAWSPATH="/var/wplex/apps/vulcano-health-check-monitor"

echo "starting vulcano-health-check-monitor application..."

java -Xms64m -Xmx256m -Dspring.config.location=$APPAWSPATH/config/application-$ENV.properties -jar $APPAWSPATH/lib/vulcano-health-check-monitor.jar &
echo $! > /var/run/vulcano-health-check-monitor.pid
echo "vulcano-health-check-monitor started."

$SHELL
