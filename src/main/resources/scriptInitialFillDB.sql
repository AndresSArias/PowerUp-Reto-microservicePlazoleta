/*
Script for the fill the initial register of dbplazoleta
*/
USE dbplazoleta;

ALTER TABLE plates 
MODIFY COLUMN active varchar(255) NOT NULL
CHECK (active IN ('true', 'false'));


INSERT INTO categorys (id, description, name) VALUES ('1', 'Es un plato de comida que puede consumirse como 1er plato, Es un plato de menor cantidad de comida que el plato principal. Las entradas pueden servirse frías o calientes. Pueden montarse en platos individuales o al centro de la mesa.','Entrada');
INSERT INTO categorys (id, description, name) VALUES ('2', 'Son preparaciones culinarias consistentes en un líquido con sustancia y mucho sabor. En algunos casos poseen ingredientes sólidos, en otros casos los ingredientes se pueden moler. Pueden prepararse sopas frías o calientes.', 'Sopa');
INSERT INTO categorys (id, description, name) VALUES ('3', 'Las ensaladas pueden estar compuestas de verduras, frutas y/o proteínas. Pueden tener ingredientes crudos y/o cocidos. Son cada vez más solicitadas por quienes buscan la tendencia natural y tienen una preocupación por una dieta baja en calorías.', 'Ensalada');
INSERT INTO categorys (id, description, name) VALUES ('4', 'Se llama así ya que es el que tiene más cantidad de comida. Es uno de los más complejos en cuanto a su elaboración. Generalmente se sirve caliente. Su contenido principal es una preparación de proteínas mas acompañamientos.', 'Plato Fuerte');
INSERT INTO categorys (id, description, name) VALUES ('5', 'El postre es cualquier plato dulce que se consume al final de una comida. Un buen postre resaltará la satisfacción de los alimentos servidos anteriormente. Para algunas personas resulta el momento más atractivo de la comida.', 'Postre');
INSERT INTO categorys (id, description, name) VALUES ('6', 'Las bebidas son aquellas obtenidas a partir de raíces, frutos y generalmente hojas de ciertas plantas, las cuales se introducen en agua hirviendo. Pueden servirse frías (más común en el caso de infusiones frutales) o calientes.', 'Bebida');
