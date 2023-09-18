INSERT INTO tb_pagamento(valor, nome, numero_do_cartao, codigo, validade, status,
pedido_id, forma_de_pagamento_id ) VALUES(1200, 'Nicodemus', '123456987', '123',
'12/30', 'CRIADO', 1, 2);
INSERT INTO tb_pagamento(valor, nome, numero_do_cartao, codigo, validade, status,
pedido_id, forma_de_pagamento_id ) VALUES(500.5, 'Amadeus', '987456321', '321',
'01/27', 'CRIADO', 2, 2);
INSERT INTO tb_pagamento(valor, nome, numero_do_cartao, codigo, validade, status,
pedido_id, forma_de_pagamento_id ) VALUES(500.5, 'Amadeus', '987456321', '321',
'01/27', 'CONFIRMADO', 2, 2);
INSERT INTO tb_pagamento(valor, status, pedido_id, forma_de_pagamento_id) VALUES(125, 'CRIADO', 3, 1);
INSERT INTO tb_pagamento(valor, status, pedido_id, forma_de_pagamento_id) VALUES(125, 'CANCELADO', 3, 1);
INSERT INTO tb_pagamento(valor, status, pedido_id, forma_de_pagamento_id) VALUES(3255, 'CRIADO', 4, 1);