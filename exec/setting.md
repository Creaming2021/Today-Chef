# Project Setting 

## Docker
```
curl -fsSL https://get.docker.com/ | sudo sh
```

## MariaDB
```
docker pull mariadb
docker run —-name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=creaming2021^^ mariadb
```

### 1. With Docker-compose

#### 1) Docker-compose
```
curl -L "https://github.com/docker/compose/releases/download/1.28.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

chmod +x /usr/local/bin/docker-compose

ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
```

#### 2) FrontEnd, API BackEnd, Nginx with Docker-Compose
```
docker-compose build
docker-compose up -d
```

### 2. Without Docker-compose

#### 1) FrontEnd
```
cd frontend
npm install
npm run build
npm run serve
```

#### 2) Streaming Client (Frontend)
```
cd frontend_streaming
npm install
npm start
```

#### 3) API BackEnd
```
cd backend
./gradlew build --exclude-task test
java -jar build/libs/*.jar  # *은 생성된 jar 파일 이름으로 입력
```

#### 4) Chat BackEnd
```
cd backend_chat
npm install
npm run start
```

#### 5) OpenVidu
```
git clone https://github.com/OpenVidu/openvidu.git
cd openvidu
mvn -DskipTests=true clean install

cd openvidu-server
mvn clean compile package

cp ../utils/* .
docker build -t openvidu/openvidu-server:TAG .
```

### Jenkins
```
docker run -d \
-u root \
-p 9090:8080 \
--name=jenkins \
-v /home/ubuntu/docker/jenkins-data:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
-v "$HOME":/home/jenkinsci/blueocean \
jenkinsci/blueocean
```