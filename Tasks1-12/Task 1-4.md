### 1
mkdir test  
cd ~/test  
cat > home_animals  
cat > pack_animals  
cat home_animals pack_animals > animals  
cat animals  
mv animals mans_friends  
### 2
cd ..  
mkdir test1  
cd ~/test  
mv mans_friends ~/test1  
### 3
sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.23-1_all.deb  
sudo dpkg -i mysql-apt-config_0.8.23-1_all.deb  
sudo apt-get update  
sudo apt-get install mysql-server  
### 4
sudo wget https://download.docker.com/linux/ubuntu/dists/jammy/pool/stable/amd64/docker-ce-cli_20.10.13~3-0~ubuntu-jammy_amd64.deb  

sudo dpkg -i docker-ce-cli_20.10.13~3-0~ubuntu-jammy_amd64.deb  

sudo dpkg -r docker-ce-cli






