Run these commands to start the application within a docker container:

```
docker build -t oregami .
docker run -p 8080:8080 -d -t oregami
```