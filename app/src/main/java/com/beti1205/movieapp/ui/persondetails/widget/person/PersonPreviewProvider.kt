package com.beti1205.movieapp.ui.persondetails.widget.person

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails

class PersonPreviewProvider : PreviewParameterProvider<PersonDetails> {
    override val values = sequenceOf(
        PersonDetails(
            id = 31,
            birthday = "1956-07-09",
            deathday = null,
            name = "Tom Hanks",
            biography = "Thomas Jeffrey Hanks (born July 9, 1956) ...",
            popularity = 69.464,
            birthPlace = "Concord, California, USA",
            personPoster = null
        ),
        PersonDetails(
            id = 238,
            birthday = "1924-04-03",
            deathday = "2004-07-01",
            name = "Marlon Brando",
            biography = "Marlon Brando Jr. (April 3, 1924 – July 1, 2004) " +
                "was an American actor. Considered one of the most influential actors of" +
                " the 20th century, he received numerous accolades throughout his career which " +
                "spanned six decades, including two Academy Awards, two Golden Globe Awards, " +
                "and three British Academy Film Awards. ",
            popularity = 17.015,
            birthPlace = "Omaha, Nebraska, USA",
            personPoster = "/fuTEPMsBtV1zE98ujPONbKiYDc2.jpg"
        ),
        PersonDetails(
            id = 238,
            birthday = "1924-04-03",
            deathday = "2004-07-01",
            name = "Marlon Brando",
            biography = "",
            popularity = 17.015,
            birthPlace = "Omaha, Nebraska, USA",
            personPoster = "/fuTEPMsBtV1zE98ujPONbKiYDc2.jpg"
        )
    )
}
