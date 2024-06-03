docker build --pull --rm -f "./Dockerfile" -t gestiondeprojet-loginapp:latest "."


docker run -d -p 8081:8081 --net=lb-net --name registery gestiondeprojet-loginapp

docker run -d --net=lb-net --name worker1 -e HOSTNAME=worker1 gestiondeprojet-loginapp
docker run -d --net=lb-net --name worker2 -e HOSTNAME=worker2 gestiondeprojet-loginapp
docker run -d --net=lb-net --name worker3 -e HOSTNAME=worker3 gestiondeprojet-loginapp