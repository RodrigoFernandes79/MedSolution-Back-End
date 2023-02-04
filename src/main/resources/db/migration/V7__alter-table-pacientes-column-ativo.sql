alter table pacientes add ativo tinyint;

update  pacientes set ativo = 1; --atualizando os pacientes já cadastrados anteriormente