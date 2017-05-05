sudo cp target/finance-compare-1.0.0-SNAPSHOT.jar /opt/finance-compare.jar
sudo systemctl stop finance-compare.service
sudo systemctl start finance-compare.service
