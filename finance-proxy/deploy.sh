sudo cp target/finance-proxy-1.0.0-SNAPSHOT.jar /opt/finance-proxy.jar
sudo systemctl stop finance-proxy.service
sudo systemctl start finance-proxy.service
