CREATE TABLE pessoa(

	codigo BIGINT(20)PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(30),
    numero VARCHAR(30),
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(30),
    cidade VARCHAR(30),
    estado VARCHAR(30),
    ativo BOOLEAN NOT NULL    

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('José','logradouro 1','1','1A','Bairro 1','0987651','cidade 1','AB',true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Maria','logradouro 2','2','1B','Bairro 1','0955651','cidade 1','AC',true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('João','logradouro 3','3','1C','Bairro 3','09832151','cidade 2','AZ',true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) VALUES ('Carlos','logradouro 4','4','1D','Bairro 2','0987999','cidade 3','AB',true);
