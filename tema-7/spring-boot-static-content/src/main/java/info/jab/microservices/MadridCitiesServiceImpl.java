package info.jab.microservices;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MadridCitiesServiceImpl implements MadridCitiesService {

    //https://en.wikipedia.org/wiki/List_of_municipalities_in_the_Community_of_Madrid
    @Override
    public List<String> getCities() {
        return List.of(
                "La Acebeda	53",
                "Ajalvir	2,526",
                "Alameda del Valle	188",
                "El Álamo	5,233",
                "Alcalá de Henares	179,602",
                "Alcobendas	95,104"
        );
        /*
        Alcorcón	149,594
        Aldea del Fresno	1,616
        Algete	16,011
        Alpedrete	8,811
        Ambite	312
        Anchuelo	590
        Aranjuez	40,928
        Arganda del Rey	33,945
        Arroyomolinos	5,428
        El Atazar	101
        Batres	1,047
        Becerril de la Sierra	3,879
        Belmonte de Tajo	1,159
        El Berrueco	391
        Berzosa del Lozoya	152
        Boadilla del Monte	27,145
        El Boalo	3,810
        Braojos	179
        Brea de Tajo	443
        Brunete	6,216
        Buitrago del Lozoya	1,668
        Bustarviejo	1,599
        Cabanillas de la Sierra	514
        La Cabrera	1,923
        Cadalso de los Vidrios	2,329
        Camarma de Esteruelas	2,950
        Campo Real	2,977
        Canencia	450
        Carabaña	1,201
        Casarrubuelos	1,090
        Cenicientos	1,885
        Cercedilla	6,037
        Cervera de Buitrago	108
        Ciempozuelos	15,075
        Cobeña	3,253
        Colmenar de Oreja	5,452
        Colmenar del Arroyo	961
        Colmenar Viejo	35,664
        Colmenarejo	5,570
        Collado Mediano	5,095
        Collado Villalba	48,885
        Corpa	452
        Coslada	79,862
        Cubas de la Sagra	2,075
        Chapinería	1,532
        Chinchón	4,346
        Daganzo de Arriba	4,725
        El Escorial	11,912
        Estremera	1,074
        Fresnedillas de la Oliva	955
        Fresno de Torote	811
        Fuenlabrada	179,735
        Fuente el Saz de Jarama	4,878
        Fuentidueña de Tajo	1,476
        Galapagar	24,927
        Garganta de los Montes	334
        Gargantilla del Lozoya y Pinilla de Buitrago	279
        Gascones	122
        Getafe	156,320
        Griñón	6,008
        Guadalix de la Sierra	3,673
        Guadarrama	11,280
        La Hiruela	88
        Horcajo de la Sierra	139
        Horcajuelo de la Sierra	109
        Hoyo de Manzanares	6,374
        Humanes de Madrid	10,561
        Leganés	174,436
        Loeches	3,489
        Lozoya	485
        Lozoyuela-Navas-Sieteiglesias	708
        Madarcos	30
        Madrid	3,016,788
        Majadahonda	52,864
        Manzanares el Real	4,688
        Meco	8,007
        Mejorada del Campo	17,560
        Miraflores de la Sierra	4,038
        El Molar	4,275
        Los Molinos	3,691
        Montejo de la Sierra	318
        Moraleja de Enmedio	3,364
        Moralzarzal	7,118
        Morata de Tajuña	5,664
        Móstoles	198,819
        Navacerrada	2,016
        Navalafuente	599
        Navalagamella	1,356
        Navalcarnero	14,936
        Navarredonda y San Mamés	112
        Navas del Rey	1,862
        Nuevo Baztán	4,083
        Olmeda de las Fuentes	150
        Orusco de Tajuña	653
        Paracuellos de Jarama	6,673
        Parla	80,545
        Patones	356
        Pedrezuela	1,776
        Pelayos de la Presa	1,597
        Perales de Tajuña	2,099
        Pezuela de las Torres	476
        Pinilla del Valle	147
        Pinto	31,737
        Piñuécar-Gandullas	179
        Pozuelo de Alarcón	71,246
        Pozuelo del Rey	219
        Prádena del Rincón	104
        Puebla de la Sierra	110
        Puentes Viejas	425
        Quijorna	1,342
        Rascafría	1,633
        Redueña	193
        Ribatejada	401
        Rivas-Vaciamadrid	39,278
        Robledillo de la Jara	103
        Robledo de Chavela (2006)	3,319
        Robregordo	80
        Las Rozas de Madrid	62,527
        Rozas de Puerto Real	257
        San Agustín del Guadalix	7,074
        San Fernando de Henares	36,658
        San Lorenzo de El Escorial	13,164
        San Martín de la Vega	12,382
        San Martín de Valdeiglesias	6,348
        San Sebastián de los Reyes	60,323
        Santa María de la Alameda	798
        Santorcaz	611
        Los Santos de la Humosa	951
        La Serna del Monte	115
        Serranillos del Valle	1,163
        Sevilla la Nueva	4,495
        Somosierra	112
        Soto del Real	7,110
        Talamanca de Jarama	1,655
        Tielmes	2,100
        Titulcia	929
        Torrejón de Ardoz	101,056
        Torrejón de la Calzada	4,847
        Torrejón de Velasco	2,333
        Torrelaguna	3,157
        Torrelodones	15,916
        Torremocha de Jarama	374
        Torres de la Alameda	4,871
        Tres Cantos	37,688
        Valdaracete	615
        Valdeavero	679
        Valdelaguna	591
        Valdemanco	499
        Valdemaqueda	630
        Valdemorillo	7,111
        Valdemoro	34,163
        Valdeolmos-Alalpardo	1,893
        Valdepiélagos	308
        Valdetorres de Jarama	2,344
        Valdilecha	2,041
        Valverde de Alcalá	315
        Velilla de San Antonio	8,188
        El Vellón	1,184
        Venturada	986
        Villa del Prado	4,350
        Villaconejos	2,972
        Villalbilla	5,138
        Villamanrique de Tajo	633
        Villamanta	1,735
        Villamantilla	363
        Villanueva de la Cañada	12,109
        Villanueva de Perales	793
        Villanueva del Pardillo	6,415
        Villar del Olmo	1,582
        Villarejo de Salvanés	5,871
        Villaviciosa de Odón	21,461
        Villavieja del Lozoya	183
        Zarzalejo	1,133
        */
    }
}
