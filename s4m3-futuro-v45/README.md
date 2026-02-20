docker build -t exemplo-aula2-v45:V1 .

docker run -p 8080:8080 -d exemplo-aula2-v45:V1

docker compose up -d  -> executar o docker-compose.yml dentro da pasta atual

docker compose down -> deletar o docker compose dessa pasta

docker compose down -v -> deleta os containeres e os volumes associados

### Subidas de imagem para o docker hub
docker login
docker push andresnunes/exemplo-aula3-v45:1
docker run andresnunes/exemplo-aula3-v45:1

### Render 
Passo a passo para novo projeto:
- Em Dashboar
- New - Web Service
- Connectar ao Github
- Ir para Link do Repo
- Adicionar Link do Repo
- Executar o Código como Docker