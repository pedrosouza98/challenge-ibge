# Challenge-IBGE

## O que é essa API ?
Se trata de uma API que retorna os Estados do Brasil juntamente com sua respectivas cidades e municípios.
Permite também a busca de uma cidade filtrada pelo seu nome.

## EndPoints
* http://localhost:8080/json/states
    * ```json
        [
            {
                "idEstado": 11,
                "siglaEstado": "RO",
                "regiaoNome": "Norte",
                "nomeCidade": "Alta Floresta D'Oeste",
                "nomeMesorregiao": "Leste Rondoniense",
                "nomeFormatado": "Alta Floresta D'Oeste/RO"
            }
        ]
        ```
      
* http://localhost:8080/csv/states
    * Retorna um CSV com modelo de dados acima
    
* http://localhost:8080/cities/name
    * ```json
        {
            "id" : 3550308
        }
      ```