# Docker Compose Example with Java Spring Boot Application
Saat sharing kemarin kan masih error ya, dikarenakan app tidak dapat terkoneksi dengan mysql. Setelah ngulik ngulik lagi terdapat suatu tag di docker compose yang dapat mengatasi error kemarin yaitu tag `restart` 

Dengan menambahkan tag `restart: on-failure` ,app yang error akan melakukan running ulang sehingga dapat terkoneksi dengan mysql

## Step by Step to running app
1. Create jar file\
   ```mvn clean install```
2. Build image with Dockerfile\
   ```docker build -t rest-template .```
3. Run with docker compose\
   ```docker-compose up```
   
## Endpoint
<table>
  <tr>
    <th>url</th>
    <td>http://localhost:8888/api/posts</td>
  </tr>
  <tr>
    <th>method</th>
    <td>GET</td>
  </tr>
</table>
