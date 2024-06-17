use biblioteca

CREATE TABLE aluno (
	ra			VARCHAR(25)		NOT NULL	UNIQUE,
	status		VARCHAR(20),
	nome		VARCHAR(70) 	NOT NULL,
	email		VARCHAR(40),
	telefone    VARCHAR(11)

	PRIMARY KEY(ra)
)

CREATE TABLE professor (
	registro	VARCHAR(25)	   NOT NULL	  UNIQUE,
	nome		VARCHAR(70) 	NOT NULL,
	email		VARCHAR(40),
	telefone    VARCHAR(11)

	PRIMARY KEY(registro)
)

CREATE TABLE autor(
	
	id		INT		IDENTITY,
	nome	VARCHAR(70)		NOT NULL,
	biografia	VARCHAR(140)	NOT NULL

	PRIMARY KEY(id)
)

CREATE TABLE livro(
	isbn		VARCHAR(15)		UNIQUE	NOT NULL,
	nome		VARCHAR(90)		UNIQUE NOT NULL,
	descricao	VARCHAR(140),
	status		VARCHAR(20),
	anoEscrito	INT,
	genero		VARCHAR(50),
	idAutor		INT				NOT NULL

	FOREIGN KEY(idAutor) REFERENCES autor (id)
	PRIMARY KEY(nome, isbn)
	)
	
CREATE TABLE reserva(
	
	id		INT		IDENTITY,
	dataDevolucao	DATE,
	dataRetirada	DATE,
	status			VARCHAR(25),
	identRa			VARCHAR(25),
	nomeLivro		VARCHAR(90),
	identReg		VARCHAR(25)

	FOREIGN KEY (nomeLivro) REFERENCES livro(nome),
	FOREIGN KEY (identRa) REFERENCES aluno (ra),
	FOREIGN KEY (identReg) REFERENCES professor(registro),
	PRIMARY KEY(id)
)
SELECT * FROM aluno