# Java-SofkaX-CPU
***EJEMPLO DE SUBRUTINA***
```
Para
subrutina= [
  "MOV 5,R00",
  "MOV 10,R01",
  "JZ 7",
  "ADD R02,R01",
  "DEC R00",
  "JMP 3",
  "MOV R02,R42"
]

La salida debe ser “50”.
```

***PONIENDO A PRUEBA LA SOLUCIÓN CON EL EJEMPLO ANTERIOR***

![alt text](https://i.imgur.com/1sffxY7.gif)

***DETALLES DE LA SOLUCIÓN***

La CPU tiene 43 registros de enteros sin signo representados en 32-bit, los cuales son nombrados R00..R42. Al inicio del programa todos los registros contienen 0. El procesador soporta las siguientes instrucciones:

  - MOV Rxx,Ryy → copia el valor del registro Rxx al registro Ryy;
  - MOV d,Rxx → copia la constante numérica d (especificada como un número decimal) al registro Rxx;
  - ADD Rxx,Ryy → calcula (Rxx + Ryy) MOD 232 y almacena el resultado en el registro Rxx;
  - DEC Rxx → disminuye el valor de Rxx en 1. Si el valor del registro es 0, al disminuirlo se genera un desbordamiento y su resultado sería 232–1;
  - INC Rxx → aumenta el valor de Rxx en 1. Si el valor del registro es 232–1, al aumentarlo se genera un desbordamiento obteniendo por resultado 0;
  - INV Rxx → ejecuta una inversión bit a bit del registro Rxx (convierte 1 en 0 y 0 en 1);
  - JMP d → salta incondicionalmente a la instrucción número d (basado en 1). Se garantiza que d es un número de instrucción válido;
  - JZ d → salta a la instrucción d (basado en 1) sólo si el registro R00 contiene 0;
  - NOP → no hace nada.

Cuando la última instrucción ha sido ejecutada, el contenido del registro R42 es considerado como el resultado de la subrutina.

Tu misión consiste en escribir un emulador para esta CPU que ejecute las subrutinas y retorne el valor resultante del registro R42.

Se garantiza que la sintaxis de todas las instrucciones de la subrutina es correcta y tiene un número de registros, constantes numéricas, y direcciones de los saltos válidos. La longitud máxima del programa es de 1024 instrucciones (el que van a evaluar, no el que van a desarrollar). El máximo número total de instrucciones que serán ejecutadas hasta que el valor sea returnado es de 5x104. (Ten en cuenta que la misma instrucción será contada tantas veces como esta sea ejecutada).

*ENTRADAS*

subrutina, un arreglo de String que contiene las instrucciones de la subrutina.
Se garantiza que este arreglo contiene al menos una instrucción y máximo 1024 instrucciones.

*SALIDA*

Retorna una cadena de texto (String) que representa un entero de 32 bits sin signo, resultante de la ejecución de las instrucciones
