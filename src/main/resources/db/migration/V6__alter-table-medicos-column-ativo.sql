alter table medicos add ativo tinyint;

update  medicos set ativo = 1; --atualizando os medicos já cadastrados anteriormente