-- Criacao do banco de dados (execute primeiro, depois conecte nele)
-- CREATE DATABASE AplicativoMusica;

-- Criacao da tabela Pessoas
CREATE TABLE pessoas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criacao da tabela Usuarios
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

-- Criacao da tabela Artistas
CREATE TABLE artistas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nacionalidade VARCHAR(100)
);

-- Criacao da tabela Bandas
CREATE TABLE bandas (
    id SERIAL PRIMARY KEY,
    nome_banda VARCHAR(255) NOT NULL
);

-- Criacao da tabela Musicas
CREATE TABLE musicas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracao INT NOT NULL -- Duracao em segundos
);

-- Criacao da tabela Playlists
CREATE TABLE playlists (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Criacao da tabela Perfis Musicais
CREATE TABLE perfis_musicais (
    id SERIAL PRIMARY KEY,
    genero_favorito VARCHAR(100) NOT NULL
);

-- Criacao da tabela de Associacao (PlaylistM - Relacao N:M entre Playlists e Musicas)
CREATE TABLE playlists_musicas (
    playlist_id INT NOT NULL,
    musica_id INT NOT NULL,
    PRIMARY KEY (playlist_id, musica_id),
    CONSTRAINT fk_playlist
        FOREIGN KEY (playlist_id) 
        REFERENCES playlists(id) 
        ON DELETE CASCADE,
    CONSTRAINT fk_musica
        FOREIGN KEY (musica_id) 
        REFERENCES musicas(id) 
        ON DELETE CASCADE
);
