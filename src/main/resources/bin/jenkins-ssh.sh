#!/bin/bash
#
# This script is used on 'post-build actions' with send build artifacts over SSH
# On SSH Publishers > Tranfers we have:
# 1.source files: target/*.tar.gz | 2.remove prefix: target | 3.exec command as:

sudo sh /var/wplex/apps/wplex-garagem/bin/stop.sh
sudo rm -rf /var/wplex/apps/wplex-garagem
sudo tar -zxvf /var/wplex/apps/artifact/wplex-garagem-*-dev.tar.gz -C /var/wplex/apps/
sudo rm -rf /var/wplex/apps/wplex-garagem/*.properties
sudo cp /var/wplex/apps/application-prd-garagem.properties /var/wplex/apps/wplex-garagem/config
sudo nohup nice /var/wplex/apps/wplex-garagem/bin/start.sh prd-garagem > /dev/null 2>&1 &
