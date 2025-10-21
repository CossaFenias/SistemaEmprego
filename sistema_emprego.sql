CREATE TABLE Provincia (
    id_provincia SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Distrito (
    id_distrito SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    id_provincia INT NOT NULL,
    FOREIGN KEY (id_provincia) REFERENCES Provincia(id_provincia),
    UNIQUE (nome, id_provincia)
);

INSERT INTO Provincia (nome) VALUES
('Niassa'),
('Cabo Delgado'),
('Nampula'),
('Zambézia'),
('Tete'),
('Manica'),
('Sofala'),
('Inhambane'),
('Gaza'),
('Maputo'),
('Cidade de Maputo');

-- Inserção dos distritos de cada província
-- Niassa
INSERT INTO Distrito (nome, id_provincia) VALUES
('Cuamba', 1),
('Lichinga', 1),
('Mandimba', 1),
('Marrupa', 1),
('Mecula', 1),
('Metangula', 1),
('Mavago', 1),
('Maúa', 1),
('Ngauma', 1),
('Sanga', 1);

-- Cabo Delgado
INSERT INTO Distrito (nome, id_provincia) VALUES
('Pemba', 2),
('Mocímboa da Praia', 2),
('Montepuez', 2),
('Mueda', 2),
('Palma', 2),
('Quissanga', 2),
('Namuno', 2),
('Nangade', 2),
('Ancuabe', 2),
('Chiúre', 2);

-- Nampula
INSERT INTO Distrito (nome, id_provincia) VALUES
('Nampula', 3),
('Angoche', 3),
('Moma', 3),
('Mogovolas', 3),
('Meconta', 3),
('Murrupula', 3),
('Mossuril', 3),
('Monapo', 3),
('Malema', 3),
('Ribáuè', 3);

-- Zambézia
INSERT INTO Distrito (nome, id_provincia) VALUES
('Quelimane', 4),
('Alto Molócuè', 4),
('Gilé', 4),
('Gurué', 4),
('Ile', 4),
('Maganja da Costa', 4),
('Milange', 4),
('Mocuba', 4),
('Morrumbala', 4),
('Namacurra', 4);

-- Tete
INSERT INTO Distrito (nome, id_provincia) VALUES
('Tete', 5),
('Angónia', 5),
('Cahora-Bassa', 5),
('Changara', 5),
('Chifunde', 5),
('Macanga', 5),
('Magoe', 5),
('Marávia', 5),
('Moatize', 5),
('Mutarara', 5);

-- Manica
INSERT INTO Distrito (nome, id_provincia) VALUES
('Chimoio', 6),
('Barué', 6),
('Gondola', 6),
('Machaze', 6),
('Macossa', 6),
('Manica', 6),
('Mossurize', 6),
('Sussundenga', 6),
('Vanduzi', 6);

-- Sofala
INSERT INTO Distrito (nome, id_provincia) VALUES
('Beira', 7),
('Buzi', 7),
('Caia', 7),
('Chemba', 7),
('Cheringoma', 7),
('Dondo', 7),
('Gorongosa', 7),
('Maringué', 7),
('Marromeu', 7),
('Muanza', 7),
('Nhamatanda', 7);

-- Inhambane
INSERT INTO Distrito (nome, id_provincia) VALUES
('Inhambane', 8),
('Funhalouro', 8),
('Govuro', 8),
('Homoíne', 8),
('Jangamo', 8),
('Mabote', 8),
('Massinga', 8),
('Maxixe', 8),
('Panda', 8),
('Vilankulo', 8),
('Zavala', 8);

-- Gaza
INSERT INTO Distrito (nome, id_provincia) VALUES
('Xai-Xai', 9),
('Bilene', 9),
('Chibuto', 9),
('Chicualacuala', 9),
('Chókwè', 9),
('Guijá', 9),
('Limpopo', 9),
('Mabalane', 9),
('Manjacaze', 9),
('Massangena', 9),
('Massingir', 9);

-- Maputo
INSERT INTO Distrito (nome, id_provincia) VALUES
('Matola', 10),
('Boane', 10),
('Magude', 10),
('Manhiça', 10),
('Marracuene', 10),
('Moamba', 10),
('Namaacha', 10);

-- Cidade de Maputo
INSERT INTO Distrito (nome, id_provincia) VALUES
('KaMpfumu', 11),
('Nhlamankulu', 11),
('KaMaxaquene', 11),
('KaMavota', 11),
('KaMubukwana', 11),
('KaTembe', 11),
('KaNyaka', 11);

CREATE TABLE Endereco (
    id_endereco SERIAL PRIMARY KEY,
    id_distrito INT NOT NULL,
    rua_avenida VARCHAR(50),
    numero VARCHAR(20),
    FOREIGN KEY (id_distrito) REFERENCES Distrito(id_distrito)
);

CREATE TABLE Empresa (
    id_empresa SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sector VARCHAR(50),
    email VARCHAR(50) UNIQUE
);

CREATE TABLE EmpresaTelefone (
    id_empresa INT NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    PRIMARY KEY (id_empresa, telefone),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

CREATE TABLE EmpresaEndereco (
    id_empresa INT NOT NULL,
    id_endereco INT NOT NULL,
    PRIMARY KEY (id_empresa, id_endereco),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa),
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE
);

CREATE TABLE ClienteTelefone (
    id_cliente INT NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_cliente, telefone),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE ClienteEndereco (
    id_cliente INT NOT NULL,
    id_endereco INT NOT NULL,
    PRIMARY KEY (id_cliente, id_endereco),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Candidato (
    id_candidato SERIAL PRIMARY KEY,
    apelido VARCHAR(20) NOT NULL,
    nome VARCHAR(30) NOT NULL,
    genero CHAR(1) CHECK (genero IN ('M','F')),
    data_nascimento DATE NOT NULL,
    email VARCHAR(50) UNIQUE,
    nacionalidade VARCHAR(80) NOT NULL,
    naturalidade VARCHAR(50)
);

CREATE TABLE CandidatoTelefone (
    id_candidato INT NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_candidato, telefone),
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato)
);

CREATE TABLE CandidatoEndereco (
    id_candidato INT NOT NULL,
    id_endereco INT NOT NULL,
    PRIMARY KEY (id_candidato, id_endereco),
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato),
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Filiacao (
    id_candidato INT PRIMARY KEY,
    nome_pai VARCHAR(50),
    nome_mae VARCHAR(50),
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato)
);

CREATE TABLE Identificacao (
    id_candidato INT PRIMARY KEY,
    numero_bi VARCHAR(15) UNIQUE NOT NULL,
    data_emissao_bi DATE,
    nuit VARCHAR(15) UNIQUE,
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato)
);

CREATE TABLE Vaga (
    id_vaga SERIAL PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    estado VARCHAR(10),
    requisitos TEXT,
    data_publicacao DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    descricao TEXT,
    actividades TEXT,
    regime VARCHAR(10),
    data_encerramento DATE,
    id_empresa INT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa)
);

CREATE TABLE VagaLocalTrabalho (
    id_vaga INT NOT NULL,
    id_endereco INT NOT NULL,
    PRIMARY KEY (id_vaga, id_endereco),
    FOREIGN KEY (id_vaga) REFERENCES Vaga(id_vaga),
    FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Candidatura (
    id_candidatura SERIAL PRIMARY KEY,
    id_candidato INT NOT NULL,
    id_vaga INT NOT NULL,
    data_candidatura DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20),
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato),
    FOREIGN KEY (id_vaga) REFERENCES Vaga(id_vaga),
    UNIQUE (id_candidato, id_vaga)
);

CREATE TABLE Servico (
    id_servico SERIAL PRIMARY KEY,
    nome_servico VARCHAR(50) NOT NULL,
    area_servico VARCHAR(50),
    descricao TEXT
);

CREATE TABLE EmpresaServico (
    id_empresa INT NOT NULL,
    id_servico INT NOT NULL,
    PRIMARY KEY (id_empresa, id_servico),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id_empresa),
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico)
);

CREATE TABLE CandidatoServico (
    id_candidato INT NOT NULL,
    id_servico INT NOT NULL,
    PRIMARY KEY (id_candidato, id_servico),
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato),
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico)
);

CREATE TABLE ClienteServico (
    id_cliente INT NOT NULL,
    id_servico INT NOT NULL,
    PRIMARY KEY (id_cliente, id_servico),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_servico) REFERENCES Servico(id_servico)
);

CREATE TABLE FormacaoAcademica (
    id_formacao SERIAL PRIMARY KEY,
    curso VARCHAR(50) NOT NULL,
    instituicao VARCHAR(50) NOT NULL,
    nivel VARCHAR(25) NOT NULL,
    ano_formacao INT CHECK (ano_formacao >= 1970),
    id_candidato INT NOT NULL,
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato)
);

CREATE TABLE ExperienciaProfissional (
    id_experiencia SERIAL PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    instituicao_empresa VARCHAR(50),
    data_inicio DATE NOT NULL,
    data_fim DATE,
    descricao TEXT,
    id_candidato INT NOT NULL,
    FOREIGN KEY (id_candidato) REFERENCES Candidato(id_candidato)
);

CREATE OR REPLACE PROCEDURE inserir_candidato(
    p_apelido VARCHAR,
    p_nome VARCHAR,
    p_genero CHAR(1),
    p_data_nascimento DATE,
    p_email VARCHAR,
    p_nacionalidade VARCHAR,
    p_naturalidade VARCHAR,
    p_telefone1 VARCHAR,
    p_telefone2 VARCHAR,
    p_id_distrito_endereco INT,
    p_rua VARCHAR,
    p_numero VARCHAR,
    p_nome_pai VARCHAR,
    p_nome_mae VARCHAR,
    p_numero_bi VARCHAR,
    p_data_emissao_bi DATE,
    p_nuit VARCHAR
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_candidato INT;
    v_id_endereco INT;
BEGIN
    INSERT INTO Candidato(apelido, nome, genero, data_nascimento, email, nacionalidade, naturalidade)
    VALUES (p_apelido, p_nome, p_genero, p_data_nascimento, p_email, p_nacionalidade, p_naturalidade)
    RETURNING id_candidato INTO v_id_candidato;

    IF p_telefone1 IS NOT NULL THEN
        INSERT INTO CandidatoTelefone(id_candidato, telefone) VALUES (v_id_candidato, p_telefone1);
    END IF;
    IF p_telefone2 IS NOT NULL THEN
        INSERT INTO CandidatoTelefone(id_candidato, telefone) VALUES (v_id_candidato, p_telefone2);
    END IF;

    INSERT INTO Endereco(id_distrito, rua_avenida, numero)
    VALUES (p_id_distrito_endereco, p_rua, p_numero)
    RETURNING id_endereco INTO v_id_endereco;

    INSERT INTO CandidatoEndereco(id_candidato, id_endereco)
    VALUES (v_id_candidato, v_id_endereco);

    INSERT INTO Filiacao(id_candidato, nome_pai, nome_mae)
    VALUES (v_id_candidato, p_nome_pai, p_nome_mae);

    INSERT INTO Identificacao(id_candidato, numero_bi, data_emissao_bi, nuit)
    VALUES (v_id_candidato, p_numero_bi, p_data_emissao_bi, p_nuit);
END;
$$;

CREATE OR REPLACE PROCEDURE inserir_empresa(
    p_nome VARCHAR,
    p_sector VARCHAR,
    p_email VARCHAR,
    p_telefone1 VARCHAR,
    p_telefone2 VARCHAR,
    p_id_distrito INT,
    p_rua VARCHAR,
    p_numero VARCHAR
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_empresa INT;
    v_id_endereco INT;
BEGIN
    INSERT INTO Empresa(nome, sector, email)
    VALUES (p_nome, p_sector, p_email)
    RETURNING id_empresa INTO v_id_empresa;

    IF p_telefone1 IS NOT NULL THEN
        INSERT INTO EmpresaTelefone(id_empresa, telefone) VALUES (v_id_empresa, p_telefone1);
    END IF;
    IF p_telefone2 IS NOT NULL THEN
        INSERT INTO EmpresaTelefone(id_empresa, telefone) VALUES (v_id_empresa, p_telefone2);
    END IF;

    INSERT INTO Endereco(id_distrito, rua_avenida, numero)
    VALUES (p_id_distrito, p_rua, p_numero)
    RETURNING id_endereco INTO v_id_endereco;

    INSERT INTO EmpresaEndereco(id_empresa, id_endereco)
    VALUES (v_id_empresa, v_id_endereco);
    
END;
$$;

CREATE OR REPLACE PROCEDURE inserir_cliente(
    p_nome VARCHAR,
    p_email VARCHAR,
    p_telefone1 VARCHAR,
    p_telefone2 VARCHAR,
    p_id_distrito INT,
    p_rua VARCHAR,
    p_numero VARCHAR
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_cliente INT;
    v_id_endereco INT;
BEGIN
    INSERT INTO Cliente(nome, email)
    VALUES (p_nome, p_email)
    RETURNING id_cliente INTO v_id_cliente;

    IF p_telefone1 IS NOT NULL THEN
        INSERT INTO ClienteTelefone(id_cliente, telefone) VALUES (v_id_cliente, p_telefone1);
    END IF;
    IF p_telefone2 IS NOT NULL THEN
        INSERT INTO ClienteTelefone(id_cliente, telefone) VALUES (v_id_cliente, p_telefone2);
    END IF;

    INSERT INTO Endereco(id_distrito, rua_avenida, numero)
    VALUES (p_id_distrito, p_rua, p_numero)
    RETURNING id_endereco INTO v_id_endereco;

    INSERT INTO ClienteEndereco(id_cliente, id_endereco)
    VALUES (v_id_cliente, v_id_endereco);
    
END;
$$;

CREATE OR REPLACE PROCEDURE publicar_vaga(
    p_id_empresa INT,
    p_titulo VARCHAR,
    p_estado VARCHAR,
    p_requisitos TEXT,
    p_descricao TEXT,
    p_actividades TEXT,
    p_regime VARCHAR,
    p_data_encerramento DATE,
    p_id_distrito INT,
    p_rua VARCHAR,
    p_numero VARCHAR
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_vaga INT;
    v_id_endereco INT;
BEGIN
    INSERT INTO Vaga(titulo, estado, requisitos, descricao, actividades, regime, data_encerramento, id_empresa)
    VALUES (p_titulo, COALESCE(p_estado, 'Ativa'), p_requisitos, p_descricao, p_actividades, p_regime, p_data_encerramento, p_id_empresa)
    RETURNING id_vaga INTO v_id_vaga;

    INSERT INTO Endereco(id_distrito, rua_avenida, numero)
    VALUES (p_id_distrito, p_rua, p_numero)
    RETURNING id_endereco INTO v_id_endereco;

    INSERT INTO VagaLocalTrabalho(id_vaga, id_endereco)
    VALUES (v_id_vaga, v_id_endereco);
    
END;
$$;

CREATE OR REPLACE PROCEDURE inserir_formacao_academica(
    p_id_candidato INT,
    p_curso VARCHAR,
    p_nivel VARCHAR,
    p_instituicao VARCHAR,
    p_ano_formacao INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO FormacaoAcademica(curso, nivel, instituicao, ano_formacao, id_candidato)
    VALUES (p_curso, p_nivel, p_instituicao, p_ano_formacao, p_id_candidato);
END;
$$;

CREATE OR REPLACE PROCEDURE inserir_experiencia_profissional(
    p_id_candidato INT,
    p_cargo VARCHAR,
    p_instituicao VARCHAR,
    p_data_inicio DATE,
    p_data_fim DATE,
    p_descricao TEXT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO ExperienciaProfissional(cargo, instituicao_empresa, data_inicio, data_fim, descricao, id_candidato)
    VALUES (p_cargo, p_instituicao, p_data_inicio, p_data_fim, p_descricao, p_id_candidato);
    
END;
$$;

CREATE OR REPLACE PROCEDURE associar_servico_candidato(
    p_id_candidato INT,
    p_id_servico INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM CandidatoServico WHERE id_candidato = p_id_candidato AND id_servico = p_id_servico
    ) THEN
        INSERT INTO CandidatoServico(id_candidato, id_servico)
        VALUES (p_id_candidato, p_id_servico);
    END IF;
    
END;
$$;

CREATE OR REPLACE PROCEDURE associar_servico_empresa(
    p_id_empresa INT,
    p_id_servico INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM EmpresaServico WHERE id_empresa = p_id_empresa AND id_servico = p_id_servico
    ) THEN
        INSERT INTO EmpresaServico(id_empresa, id_servico)
        VALUES (p_id_empresa, p_id_servico);
    END IF;
    
END;
$$;

CREATE OR REPLACE PROCEDURE associar_servico_cliente(
    p_id_cliente INT,
    p_id_servico INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM ClienteServico WHERE id_cliente = p_id_cliente AND id_servico = p_id_servico
    ) THEN
        INSERT INTO ClienteServico(id_cliente, id_servico)
        VALUES (p_id_cliente, p_id_servico);
    END IF;
    
END;
$$;

CREATE OR REPLACE PROCEDURE remover_candidato(
    p_id_candidato INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM CandidatoServico WHERE id_candidato = p_id_candidato;
    DELETE FROM FormacaoAcademica WHERE id_candidato = p_id_candidato;
    DELETE FROM ExperienciaProfissional WHERE id_candidato = p_id_candidato;
    DELETE FROM Filiacao WHERE id_candidato = p_id_candidato;
    DELETE FROM Identificacao WHERE id_candidato = p_id_candidato;
    DELETE FROM CandidatoEndereco WHERE id_candidato = p_id_candidato;
    DELETE FROM CandidatoTelefone WHERE id_candidato = p_id_candidato;
    DELETE FROM Candidato WHERE id_candidato = p_id_candidato;
END;
$$;

CREATE OR REPLACE FUNCTION calcular_idade_candidato(id_candidato INT)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    idade INT;
BEGIN
    SELECT DATE_PART('year', AGE(data_nascimento))
    INTO idade
    FROM Candidato
    WHERE id_candidato = $1;
    RETURN idade;
END;
$$;

CREATE OR REPLACE FUNCTION contar_vagas_empresa(id_empresa INT)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    total_vagas INT;
BEGIN
    SELECT COUNT(*)
    INTO total_vagas
    FROM Vaga
    WHERE id_empresa = $1;
    RETURN total_vagas;
END;
$$;

CREATE OR REPLACE FUNCTION candidatos_por_servico(id_servico INT)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    total_candidatos INT;
BEGIN
    SELECT COUNT(*)
    INTO total_candidatos
    FROM CandidatoServico
    WHERE id_servico = $1;
    RETURN total_candidatos;
END;
$$;

CREATE OR REPLACE FUNCTION experiencia_total(id_candidato INT)
RETURNS TEXT
LANGUAGE plpgsql
AS $$
DECLARE
    total_meses INT;
    anos INT;
    meses INT;
BEGIN
    SELECT COALESCE(SUM(
        DATE_PART('year', AGE(COALESCE(data_fim, CURRENT_DATE), data_inicio)) * 12 +
        DATE_PART('month', AGE(COALESCE(data_fim, CURRENT_DATE), data_inicio))
    ), 0)
    INTO total_meses
    FROM ExperienciaProfissional
    WHERE id_candidato = $1;
    anos := total_meses / 12;
    meses := total_meses % 12;
    RETURN anos || ' anos e ' || meses || ' meses';
END;
$$;

CREATE OR REPLACE FUNCTION formacoes_do_candidato(id_candidato INT)
RETURNS JSON
LANGUAGE plpgsql
AS $$
DECLARE
    formacoes JSON;
BEGIN
    SELECT JSON_AGG(
        JSON_BUILD_OBJECT(
            'curso', curso,
            'ano_formacao', ano_formacao
        )
    )
    INTO formacoes
    FROM FormacaoAcademica
    WHERE id_candidato = $1;
    RETURN COALESCE(formacoes, '[]'::JSON);
END;
$$;

CREATE OR REPLACE FUNCTION vagas_ativas_no_distrito(id_distrito INT)
RETURNS TABLE(
    id_vaga INT,
    titulo VARCHAR,
    empresa VARCHAR,
    data_encerramento DATE
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT 
        v.id_vaga,
        v.titulo,
        e.nome as empresa,
        v.data_encerramento
    FROM Vaga v
    JOIN Empresa e ON v.id_empresa = e.id_empresa
    JOIN VagaLocalTrabalho vlt ON v.id_vaga = vlt.id_vaga
    JOIN Endereco en ON vlt.id_endereco = en.id_endereco
    WHERE en.id_distrito = $1
    AND v.estado = 'Ativa';
END;
$$;

CREATE OR REPLACE FUNCTION candidato_tem_email(id_candidato INT)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
DECLARE
    tem_email BOOLEAN;
BEGIN
    SELECT email IS NOT NULL
    INTO tem_email
    FROM Candidato
    WHERE id_candidato = $1;
    RETURN tem_email;
END;
$$;

CREATE OR REPLACE FUNCTION empresa_tem_servicos(id_empresa INT)
RETURNS BOOLEAN
LANGUAGE plpgsql
AS $$
DECLARE
    tem_servicos BOOLEAN;
BEGIN
    SELECT EXISTS(
        SELECT 1 
        FROM EmpresaServico 
        WHERE id_empresa = $1
    )
    INTO tem_servicos;
    RETURN tem_servicos;
END;
$$;

CREATE OR REPLACE FUNCTION progresso_candidato(id_candidato INT)
RETURNS DECIMAL
LANGUAGE plpgsql
AS $$
DECLARE
    total_campos CONSTANT INT := 7;
    campos_preenchidos INT := 0;
BEGIN
    IF (SELECT COUNT(*) FROM FormacaoAcademica WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    IF (SELECT COUNT(*) FROM ExperienciaProfissional WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    IF (SELECT COUNT(*) FROM CandidatoServico WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    IF (SELECT COUNT(*) FROM CandidatoTelefone WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    IF (SELECT COUNT(*) FROM CandidatoEndereco WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    IF (SELECT COUNT(*) FROM Filiacao WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    IF (SELECT COUNT(*) FROM Identificacao WHERE id_candidato = $1) > 0 THEN
        campos_preenchidos := campos_preenchidos + 1;
    END IF;
    RETURN ROUND((campos_preenchidos::DECIMAL / total_campos) * 100, 2);
END;
$$;

CREATE OR REPLACE FUNCTION dias_ate_encerramento(id_vaga INT)
RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    dias_restantes INT;
BEGIN
    SELECT (data_encerramento - CURRENT_DATE)
    INTO dias_restantes
    FROM Vaga
    WHERE id_vaga = $1;
    RETURN dias_restantes;
END;
$$;

CREATE OR REPLACE FUNCTION verificar_email_empresa()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM Empresa
        WHERE email = NEW.email
        AND id_empresa <> COALESCE(NEW.id_empresa, 0)
    ) THEN
        RAISE EXCEPTION 'Email já existe em outra empresa';
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER before_insert_email_empresa
    BEFORE INSERT OR UPDATE ON Empresa
    FOR EACH ROW
    EXECUTE FUNCTION verificar_email_empresa();

CREATE OR REPLACE FUNCTION definir_estado_vaga()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.estado IS NULL THEN
        NEW.estado := 'Ativa';
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER after_insert_vaga
    BEFORE INSERT ON Vaga
    FOR EACH ROW
    EXECUTE FUNCTION definir_estado_vaga();

CREATE OR REPLACE FUNCTION validar_datas_experiencia()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.data_fim IS NOT NULL AND NEW.data_fim < NEW.data_inicio THEN
        RAISE EXCEPTION 'Data de fim não pode ser anterior à data de início';
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER before_update_data_fim_experiencia
    BEFORE INSERT OR UPDATE ON ExperienciaProfissional
    FOR EACH ROW
    EXECUTE FUNCTION validar_datas_experiencia();

CREATE OR REPLACE FUNCTION verificar_unicidade_documentos()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.numero_bi IS NOT NULL AND EXISTS (
        SELECT 1 FROM Identificacao 
        WHERE numero_bi = NEW.numero_bi AND id_candidato != NEW.id_candidato
    ) THEN
        RAISE EXCEPTION 'Número de BI já existe';
    END IF;
    IF NEW.nuit IS NOT NULL AND EXISTS (
        SELECT 1 FROM Identificacao 
        WHERE nuit = NEW.nuit AND id_candidato != NEW.id_candidato
    ) THEN
        RAISE EXCEPTION 'NUIT já existe';
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER after_insert_identificacao
    BEFORE INSERT OR UPDATE ON Identificacao
    FOR EACH ROW
    EXECUTE FUNCTION verificar_unicidade_documentos();

CREATE OR REPLACE FUNCTION limpar_servicos_empresa()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM EmpresaServico WHERE id_empresa = OLD.id_empresa;
    RETURN OLD;
END;
$$;

CREATE TRIGGER after_delete_empresa
    AFTER DELETE ON Empresa
    FOR EACH ROW
    EXECUTE FUNCTION limpar_servicos_empresa();

CREATE OR REPLACE FUNCTION formatar_telefone()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    NEW.telefone := regexp_replace(NEW.telefone, '[^0-9]', '', 'g');
    IF LENGTH(NEW.telefone) = 9 AND NEW.telefone NOT LIKE '+%' THEN
        NEW.telefone := '+258' || NEW.telefone;
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER before_insert_telefone_empresa
    BEFORE INSERT OR UPDATE ON EmpresaTelefone
    FOR EACH ROW
    EXECUTE FUNCTION formatar_telefone();

CREATE TRIGGER before_insert_telefone_cliente
    BEFORE INSERT OR UPDATE ON ClienteTelefone
    FOR EACH ROW
    EXECUTE FUNCTION formatar_telefone();

CREATE TRIGGER before_insert_telefone_candidato
    BEFORE INSERT OR UPDATE ON CandidatoTelefone
    FOR EACH ROW
    EXECUTE FUNCTION formatar_telefone();

CREATE OR REPLACE FUNCTION evitar_duplicacao_endereco_vaga()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
    v_id_empresa INT;
BEGIN
    SELECT id_empresa INTO v_id_empresa
    FROM Vaga WHERE id_vaga = NEW.id_vaga;
    IF EXISTS (
        SELECT 1 
        FROM Vaga v
        JOIN VagaLocalTrabalho vlt ON v.id_vaga = vlt.id_vaga
        WHERE v.id_empresa = v_id_empresa
        AND vlt.id_endereco = NEW.id_endereco
        AND v.id_vaga != NEW.id_vaga
    ) THEN
        RAISE EXCEPTION 'Esta empresa já tem uma vaga neste endereço';
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER before_insert_vaga_local_trabalho
    BEFORE INSERT ON VagaLocalTrabalho
    FOR EACH ROW
    EXECUTE FUNCTION evitar_duplicacao_endereco_vaga();

CREATE OR REPLACE FUNCTION notificar_novo_cliente()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    PERFORM pg_notify('novo_cliente', 
        json_build_object(
            'id_cliente', NEW.id_cliente,
            'nome', NEW.nome,
            'email', NEW.email,
            'timestamp', CURRENT_TIMESTAMP
        )::text
    );
    RETURN NEW;
END;
$$;

CREATE TRIGGER after_insert_cliente
    AFTER INSERT ON Cliente
    FOR EACH ROW
    EXECUTE FUNCTION notificar_novo_cliente();

CREATE OR REPLACE VIEW vw_candidatos_completos AS
SELECT 
    c.id_candidato,
    c.apelido,
    c.nome,
    c.genero,
    c.data_nascimento,
    c.email,
    c.nacionalidade,
    c.naturalidade,
    p.nome as provincia,
    ci.nome as distrito,
    e.rua_avenida,
    e.numero,
    STRING_AGG(DISTINCT ct.telefone, ', ') as telefones,
    f.nome_pai,
    f.nome_mae,
    i.numero_bi,
    i.data_emissao_bi,
    i.nuit
FROM Candidato c
LEFT JOIN CandidatoEndereco ce ON c.id_candidato = ce.id_candidato
LEFT JOIN Endereco e ON ce.id_endereco = e.id_endereco
LEFT JOIN Distrito ci ON e.id_distrito = ci.id_distrito
LEFT JOIN Provincia p ON ci.id_provincia = p.id_provincia
LEFT JOIN CandidatoTelefone ct ON c.id_candidato = ct.id_candidato
LEFT JOIN Filiacao f ON c.id_candidato = f.id_candidato
LEFT JOIN Identificacao i ON c.id_candidato = i.id_candidato
GROUP BY 
    c.id_candidato, p.nome, ci.nome, e.rua_avenida, 
    e.numero, f.nome_pai, f.nome_mae, i.numero_bi, 
    i.data_emissao_bi, i.nuit;

CREATE OR REPLACE VIEW vw_vagas_abertas AS
SELECT 
    v.id_vaga,
    v.titulo,
    v.estado,
    v.requisitos,
    v.data_publicacao,
    v.descricao,
    v.actividades,
    v.regime,
    v.data_encerramento,
    e.nome as empresa
FROM Vaga v
JOIN Empresa e ON v.id_empresa = e.id_empresa
WHERE v.estado = 'Ativa';

CREATE OR REPLACE VIEW vw_empresas_com_servicos AS
SELECT 
    e.id_empresa,
    e.nome,
    e.sector,
    e.email,
    COUNT(es.id_servico) as quantidade_servicos,
    STRING_AGG(DISTINCT s.nome_servico, ', ') as servicos
FROM Empresa e
JOIN EmpresaServico es ON e.id_empresa = es.id_empresa
JOIN Servico s ON es.id_servico = s.id_servico
GROUP BY e.id_empresa, e.nome, e.sector, e.email;

CREATE OR REPLACE VIEW vw_candidatos_por_servico AS
SELECT 
    s.id_servico,
    s.nome_servico,
    s.area_servico,
    COUNT(cs.id_candidato) as total_candidatos
FROM Servico s
LEFT JOIN CandidatoServico cs ON s.id_servico = cs.id_servico
GROUP BY s.id_servico, s.nome_servico, s.area_servico
ORDER BY total_candidatos DESC;

CREATE OR REPLACE VIEW vw_candidatos_experiencia AS
SELECT 
    c.id_candidato,
    c.apelido,
    c.nome,
    COALESCE(
        ROUND(
            SUM(
                EXTRACT(YEAR FROM AGE(COALESCE(ep.data_fim, CURRENT_DATE), ep.data_inicio)) +
                EXTRACT(MONTH FROM AGE(COALESCE(ep.data_fim, CURRENT_DATE), ep.data_inicio)) / 12.0
            ), 1
        ), 0
    ) as anos_experiencia
FROM Candidato c
LEFT JOIN ExperienciaProfissional ep ON c.id_candidato = ep.id_candidato
GROUP BY c.id_candidato, c.apelido, c.nome;

CREATE OR REPLACE VIEW vw_formacao_candidatos AS
SELECT 
    c.id_candidato,
    c.apelido,
    c.nome,
    fa.curso,
    fa.instituicao,
    fa.ano_formacao
FROM Candidato c
JOIN FormacaoAcademica fa ON c.id_candidato = fa.id_candidato
ORDER BY c.apelido, c.nome, fa.ano_formacao DESC;

CREATE OR REPLACE VIEW vw_clientes_enderecos AS
SELECT 
    cl.id_cliente,
    cl.nome,
    cl.email,
    p.nome as provincia,
    ci.nome as distrito,
    e.rua_avenida,
    e.numero
FROM Cliente cl
JOIN ClienteEndereco cle ON cl.id_cliente = cle.id_cliente
JOIN Endereco e ON cle.id_endereco = e.id_endereco
JOIN Distrito ci ON e.id_distrito = ci.id_distrito
JOIN Provincia p ON ci.id_provincia = p.id_provincia;

CREATE OR REPLACE VIEW vw_empresas_vagas AS
SELECT 
    e.id_empresa,
    e.nome as empresa,
    e.sector,
    COUNT(v.id_vaga) as total_vagas,
    SUM(CASE WHEN v.estado = 'Ativa' THEN 1 ELSE 0 END) as vagas_ativas
FROM Empresa e
LEFT JOIN Vaga v ON e.id_empresa = v.id_empresa
GROUP BY e.id_empresa, e.nome, e.sector
ORDER BY total_vagas DESC;

CREATE OR REPLACE VIEW vw_vagas_localizacao AS
SELECT 
    v.id_vaga,
    v.titulo,
    v.estado,
    v.data_encerramento,
    e.nome as empresa,
    p.nome as provincia,
    ci.nome as distrito,
    en.rua_avenida,
    en.numero
FROM Vaga v
JOIN Empresa e ON v.id_empresa = e.id_empresa
JOIN VagaLocalTrabalho vlt ON v.id_vaga = vlt.id_vaga
JOIN Endereco en ON vlt.id_endereco = en.id_endereco
JOIN Distrito ci ON en.id_distrito = ci.id_distrito
JOIN Provincia p ON ci.id_provincia = p.id_provincia
WHERE v.estado = 'Ativa';

CREATE OR REPLACE VIEW vw_servicos_gerais AS
SELECT 
    s.id_servico,
    s.nome_servico,
    s.area_servico,
    s.descricao,
    COUNT(DISTINCT es.id_empresa) as total_empresas,
    COUNT(DISTINCT cs.id_candidato) as total_candidatos,
    COUNT(DISTINCT cls.id_cliente) as total_clientes
FROM Servico s
LEFT JOIN EmpresaServico es ON s.id_servico = es.id_servico
LEFT JOIN CandidatoServico cs ON s.id_servico = cs.id_servico
LEFT JOIN ClienteServico cls ON s.id_servico = cls.id_servico
GROUP BY s.id_servico, s.nome_servico, s.area_servico, s.descricao;
