docker build -t exemplo-aula2-v45:V1 .

docker run -p 8080:8080 -d exemplo-aula2-v45:V1

docker compose up -d  -> executar o docker-compose.yml dentro da pasta atual

docker compose down -> deletar o docker compose dessa pasta

docker compose down -v -> deleta os containeres e os volumes associados