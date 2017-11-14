#!/bin/bash
#
# This script is used on 'post-build actions' with send build artifacts over SSH
# On SSH Publishers > Tranfers we have:
# 1.source files: target/*.tar.gz | 2.remove prefix: target | 3.exec command as:

sudo sh /var/wplex/apps/vulcano-health-check-monitor/bin/stop.sh
sudo rm -rf /var/wplex/apps/vulcano-health-check-monitor
sudo tar -zxvf /var/wplex/apps/artifact/vulcano-health-check-monitor-*-dev.tar.gz -C /var/wplex/apps/
sudo rm -rf /var/wplex/apps/vulcano-health-check-monitor/*.properties
sudo cp /var/wplex/apps/application-prd-health-monitor.properties /var/wplex/apps/vulcano-health-check-monitor/config
sudo nohup nice /var/wplex/apps/vulcano-health-check-monitor/bin/start.sh prd-health-monitor > /dev/null 2>&1 &
