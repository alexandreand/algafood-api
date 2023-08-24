insert into cozinha (id, nome) value (1, 'Tailandesa');
insert into cozinha (id, nome) value (2, 'Indiana');


insert into Restaurante (nome, taxa_frete, cozinha_id) value ('Restaurante 1', 2, 2);
insert into Restaurante (nome, taxa_frete, cozinha_id) value ('Restaurante 2', 3, 1);
insert into Restaurante (nome, taxa_frete, cozinha_id) value ('Restaurante 3', 5, 2);


insert into forma_pagamento (descricao) value ('Boleto');
insert into forma_pagamento (descricao) value ('Boleto');
insert into forma_pagamento (descricao) value ('Pix');
insert into forma_pagamento (descricao) value ('Cartão de Crédito');
insert into forma_pagamento (descricao) value ('Em Espécie');

insert into permissao (descricao) value ('Dono de restaurante');
insert into permissao (descricao) value ('Cliente');

insert into estado (nome) value ('Rio Grande do Norte');
insert into estado (nome) value ('Rio Grande do Sul');

insert into cidade (nome, estado_id) value ('Natal', 1);
insert into cidade (nome, estado_id) value ('Porto Alegre', 2);