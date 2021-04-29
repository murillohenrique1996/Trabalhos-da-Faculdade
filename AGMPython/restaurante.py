#algoritmo genetico
#problema do restaurante, onde temos uma lista de pedidos que precisa ser otimizada.


#exemplo basico com 10 cromossomos, 10 genes, 100 geracoes
#recurso maximo = 10

import numpy as np
import copy
from random import randrange
from sklearn.utils import shuffle

#variaveis de auxilio
i = 0
j = 0

#pontuacao do cromossomo
score_global = 0

#recursos atuais
recurso = 0



#gerar populacao
mapatempo = []
maparecurso = []

for i in range(0,10):
    mapatempo.append(randrange(9) + 1)
for i in range(0,10):
    maparecurso.append(randrange(9) + 1)

mapacopiaT = copy.deepcopy(mapatempo)
mapacopiaR = copy.deepcopy(maparecurso)

mapacopiaT, mapacopiaR = shuffle(mapatempo, maparecurso, random_state = 0)

mapageralT = []
mapageralR = []

for i in range(0,10):
    mapacopiaT, mapacopiaR = shuffle(mapatempo, maparecurso, random_state=i)
    mapageralT = mapageralT + mapacopiaT
    mapageralR = mapageralR + mapacopiaR

print(mapageralT)
print(mapageralR)
#populacao gerada


#crossover
mapageralcopiaT = copy.deepcopy(mapageralT)
mapageralcopiaR = copy.deepcopy(mapageralR)











# talvez reusavel
"""mapa = np.random.randint(low= 1, high = 10, size=(2,10))
print(mapa)

while(True):
    print("CICLO" , score_global)
    score_global = score_global + 1
    while(True):
        recurso = recurso + mapa[1][j]
        print("recurso", recurso)
        j = j + 1
        print("J", j)
        if recurso > 10:
            recurso = recurso - mapa[1][j]
            j= j - 1
            for i in range(0 , j):
                if score_global == mapa[0][i]:
                    recurso = recurso - mapa[0][i]
                    break
        break

    print("score_global", score_global)


print(mapa)
"""

