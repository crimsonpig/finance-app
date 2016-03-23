sudo cp target/finance-app-1.0.0-SNAPSHOT.jar /opt/finance-app.jar
sudo systemctl stop finance-app.service
sudo systemctl start finance-app.service
