package com.naufalprakoso.moviesjetpack.ui.utils

import com.naufalprakoso.moviesjetpack.data.source.local.entity.MovieEntity
import com.naufalprakoso.moviesjetpack.data.source.local.entity.TvShowEntity
import com.naufalprakoso.moviesjetpack.data.source.remote.response.MovieResponse
import com.naufalprakoso.moviesjetpack.data.source.remote.response.TvShowResponse

class FakeDataDummy {
    companion object {
        fun generateMovies() : ArrayList<MovieEntity>{
            val movies: ArrayList<MovieEntity> = ArrayList()

            movies.add(
                MovieEntity(
                    "429617",
                    "Spider-Man: Far from Home",
                    "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                    "65",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg",
                    "2019",
                    "2h 9m"
                )
            )
            movies.add(
                MovieEntity(
                    "301528",
                    "Toy Story 4",
                    "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "77",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                    "2019",
                    "1h 40m"
                )
            )
            movies.add(
                MovieEntity(
                    "315635",
                    "Spider-Man: Homecoming",
                    "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "74",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/kY2c7wKgOfQjvbqe7yVzLTYkxJO.jpg",
                    "2017",
                    "2h 13m"
                )
            )
            movies.add(
                MovieEntity(
                    "284052",
                    "Doctor Strange",
                    "After his career is destroyed, a brilliant but arrogant surgeon gets a new lease on life when a sorcerer takes him under her wing and trains him to defend the world against evil.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "73",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/4PiiNGXj1KENTmCBHeN6Mskj2Fq.jpg",
                    "2016",
                    "1h 55m"
                )
            )
            movies.add(
                MovieEntity(
                    "100402",
                    "Captain America: The Winter Soldier",
                    "After the cataclysmic events in New York with The Avengers, Steve Rogers, aka Captain America is living quietly in Washington, D.C. and trying to adjust to the modern world. But when a S.H.I.E.L.D. colleague comes under attack, Steve becomes embroiled in a web of intrigue that threatens to put the world at risk. Joining forces with the Black Widow, Captain America struggles to expose the ever-widening conspiracy while fighting off professional assassins sent to silence him at every turn. When the full scope of the villainous plot is revealed, Captain America and the Black Widow enlist the help of a new ally, the Falcon. However, they soon find themselves up against an unexpected and formidable enemy—the Winter Soldier.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "77",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/5TQ6YDmymBpnF005OyoB7ohZps9.jpg",
                    "2014",
                    "2h 16m"
                )
            )
            movies.add(
                MovieEntity(
                    "68721",
                    "Iron Man 3",
                    "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "69",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/7XiGqZE8meUv7L4720L0tIDd7gO.jpg",
                    "2013",
                    "2h 11m"
                )
            )
            movies.add(
                MovieEntity(
                    "1771",
                    "Captain America: The First Avenger",
                    "During World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "69",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg",
                    "2011",
                    "2h 4m"
                )
            )
            movies.add(
                MovieEntity(
                    "283995",
                    "Guardians of the Galaxy Vol. 2",
                    "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",
                    "77",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg",
                    "2017",
                    "2h 11m"
                )
            )
            movies.add(
                MovieEntity(
                    "271110",
                    "Captain America: Civil War",
                    "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.",
                    "74",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/kSBXou5Ac7vEqKd97wotJumyJvU.jpg",
                    "2016",
                    "2h 27m"
                )
            )
            movies.add(
                MovieEntity(
                    "284053",
                    "Thor: Ragnarok",
                    "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.",
                    "75",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg",
                    "2017",
                    "2h 11m"
                )
            )

            return movies
        }

        fun generateRemoteDummyMovies() : ArrayList<MovieResponse>{
            val movies = ArrayList<MovieResponse>()

            movies.add(
                MovieResponse(
                    "429617",
                    "Spider-Man: Far from Home",
                    "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                    "65",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/2cAc4qH9Uh2NtSujJ90fIAMrw7T.jpg",
                    "2019",
                    "2h 9m"
                )
            )
            movies.add(
                MovieResponse(
                    "301528",
                    "Toy Story 4",
                    "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "77",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
                    "2019",
                    "1h 40m"
                )
            )
            movies.add(
                MovieResponse(
                    "315635",
                    "Spider-Man: Homecoming",
                    "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "74",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/kY2c7wKgOfQjvbqe7yVzLTYkxJO.jpg",
                    "2017",
                    "2h 13m"
                )
            )
            movies.add(
                MovieResponse(
                    "284052",
                    "Doctor Strange",
                    "After his career is destroyed, a brilliant but arrogant surgeon gets a new lease on life when a sorcerer takes him under her wing and trains him to defend the world against evil.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "73",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/4PiiNGXj1KENTmCBHeN6Mskj2Fq.jpg",
                    "2016",
                    "1h 55m"
                )
            )
            movies.add(
                MovieResponse(
                    "100402",
                    "Captain America: The Winter Soldier",
                    "After the cataclysmic events in New York with The Avengers, Steve Rogers, aka Captain America is living quietly in Washington, D.C. and trying to adjust to the modern world. But when a S.H.I.E.L.D. colleague comes under attack, Steve becomes embroiled in a web of intrigue that threatens to put the world at risk. Joining forces with the Black Widow, Captain America struggles to expose the ever-widening conspiracy while fighting off professional assassins sent to silence him at every turn. When the full scope of the villainous plot is revealed, Captain America and the Black Widow enlist the help of a new ally, the Falcon. However, they soon find themselves up against an unexpected and formidable enemy—the Winter Soldier.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "77",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/5TQ6YDmymBpnF005OyoB7ohZps9.jpg",
                    "2014",
                    "2h 16m"
                )
            )
            movies.add(
                MovieResponse(
                    "68721",
                    "Iron Man 3",
                    "When Tony Stark's world is torn apart by a formidable terrorist called the Mandarin, he starts an odyssey of rebuilding and retribution.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "69",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/7XiGqZE8meUv7L4720L0tIDd7gO.jpg",
                    "2013",
                    "2h 11m"
                )
            )
            movies.add(
                MovieResponse(
                    "1771",
                    "Captain America: The First Avenger",
                    "During World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.\"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                    "69",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg",
                    "2011",
                    "2h 4m"
                )
            )
            movies.add(
                MovieResponse(
                    "283995",
                    "Guardians of the Galaxy Vol. 2",
                    "The Guardians must fight to keep their newfound family together as they unravel the mysteries of Peter Quill's true parentage.",
                    "77",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/y4MBh0EjBlMuOzv9axM4qJlmhzz.jpg",
                    "2017",
                    "2h 11m"
                )
            )
            movies.add(
                MovieResponse(
                    "271110",
                    "Captain America: Civil War",
                    "Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.",
                    "74",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/kSBXou5Ac7vEqKd97wotJumyJvU.jpg",
                    "2016",
                    "2h 27m"
                )
            )
            movies.add(
                MovieResponse(
                    "284053",
                    "Thor: Ragnarok",
                    "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his home-world and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.",
                    "75",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg",
                    "2017",
                    "2h 11m"
                )
            )

            return movies
        }

        fun generateTvShows() : ArrayList<TvShowEntity>{
            val tvShows: ArrayList<TvShowEntity> = ArrayList()

            tvShows.add(
                TvShowEntity(
                    "61819",
                    "Daredevil Season 1",
                    "Blinded as a young boy, Matt Murdock fights injustice by day as a lawyer and by night as the superhero Daredevil in Hell's Kitchen, New York City.",
                    "78",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/zFmJQzl6bFrdpHhDkxXmboyykqD.jpg",
                    "2015",
                    "13"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "61829",
                    "Daredevil Season 2",
                    "Dark forces are tearing Hell's Kitchen apart once again, but this time Daredevil may have to choose between the city and his friends.",
                    "88",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/opHoslNCxkgoCaGhfO66fvCSH83.jpg",
                    "2016",
                    "13"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "61839",
                    "Daredevil Season 3",
                    "Missing for months, Matt Murdock reemerges a broken man, putting into question his future as both vigilante Daredevil and lawyer Matthew Murdock. But when his archenemy Wilson Fisk is released from prison, Matt must choose between hiding from the world, or embracing his destiny as a hero.",
                    "99",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/zH6sgePlr1XX0jSZypbfQmr70Lf.jpg",
                    "2018",
                    "13"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "66712",
                    "Stranger Things",
                    "Strange things are afoot in Hawkins, Indiana, where a young boy's sudden disappearance unearths a young girl with otherworldly powers.",
                    "98",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/xRTUb8oeQHGjyBWj7OOpkvUuvKO.jpg",
                    "2016",
                    "8"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "66722",
                    "Stranger Things 2",
                    "It's been nearly a year since Will's strange disappearance. But life's hardly back to normal in Hawkins. Not even close.",
                    "99",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/lXS60geme1LlEob5Wgvj3KilClA.jpg",
                    "2017",
                    "9"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "66732",
                    "Stranger Things 3",
                    "One Summer can change Everything.",
                    "100",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg",
                    "2019",
                    "8"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "75006",
                    "The Umbrella Academy",
                    "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                    "77",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/uYHdIs5O8tiU5p6MvUPd2jElOH6.jpg",
                    "2019",
                    "10"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "38412",
                    "Jessica Jones",
                    "Haunted by a traumatic past, Jessica Jones uses her gifts as a private eye to find her tormentor before he can harm anyone else in Hell's Kitchen.",
                    "87",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/4XbwCmEr72fQhMZ8CYONB2kxtXY.jpg",
                    "2015",
                    "13"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "38422",
                    "Jessica Jones Season 2",
                    "Drowning in anger, Jessica Jones is forced to reckon with her past, her powers and her newfound fame as she dishes out her own messy form of justice. Finally ready to face her past, Jessica hunts down the source of her powers and uncovers a link to a shadowy killer who's terrorizing the city.",
                    "88",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/7pJP2YLAz1fcyqk0VlvcvxsyUCm.jpg",
                    "2018",
                    "13"
                )
            )
            tvShows.add(
                TvShowEntity(
                    "38432",
                    "Jessica Jones Season 3",
                    "In the final season, Jessica matches wits with a calculating serial killer, and a newly powered Trish goes to extremes to stamp out evil.",
                    "89",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/5HyBZrWyZVTTClijgk4ivITDMe.jpg",
                    "2019",
                    "13"
                )
            )

            return tvShows
        }

        fun generateRemoteDummyTvShows() : ArrayList<TvShowResponse>{
            val tvShows: ArrayList<TvShowResponse> = ArrayList()

            tvShows.add(
                TvShowResponse(
                    "61819",
                    "Daredevil Season 1",
                    "Blinded as a young boy, Matt Murdock fights injustice by day as a lawyer and by night as the superhero Daredevil in Hell's Kitchen, New York City.",
                    "78",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/zFmJQzl6bFrdpHhDkxXmboyykqD.jpg",
                    "2015",
                    "13"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "61829",
                    "Daredevil Season 2",
                    "Dark forces are tearing Hell's Kitchen apart once again, but this time Daredevil may have to choose between the city and his friends.",
                    "88",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/opHoslNCxkgoCaGhfO66fvCSH83.jpg",
                    "2016",
                    "13"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "61839",
                    "Daredevil Season 3",
                    "Missing for months, Matt Murdock reemerges a broken man, putting into question his future as both vigilante Daredevil and lawyer Matthew Murdock. But when his archenemy Wilson Fisk is released from prison, Matt must choose between hiding from the world, or embracing his destiny as a hero.",
                    "99",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/zH6sgePlr1XX0jSZypbfQmr70Lf.jpg",
                    "2018",
                    "13"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "66712",
                    "Stranger Things",
                    "Strange things are afoot in Hawkins, Indiana, where a young boy's sudden disappearance unearths a young girl with otherworldly powers.",
                    "98",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/xRTUb8oeQHGjyBWj7OOpkvUuvKO.jpg",
                    "2016",
                    "8"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "66722",
                    "Stranger Things 2",
                    "It's been nearly a year since Will's strange disappearance. But life's hardly back to normal in Hawkins. Not even close.",
                    "99",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/lXS60geme1LlEob5Wgvj3KilClA.jpg",
                    "2017",
                    "9"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "66732",
                    "Stranger Things 3",
                    "One Summer can change Everything.",
                    "100",
                    "Adventure",
                    "https://image.tmdb.org/t/p/w1280/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg",
                    "2019",
                    "8"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "75006",
                    "The Umbrella Academy",
                    "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                    "77",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/uYHdIs5O8tiU5p6MvUPd2jElOH6.jpg",
                    "2019",
                    "10"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "38412",
                    "Jessica Jones",
                    "Haunted by a traumatic past, Jessica Jones uses her gifts as a private eye to find her tormentor before he can harm anyone else in Hell's Kitchen.",
                    "87",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/4XbwCmEr72fQhMZ8CYONB2kxtXY.jpg",
                    "2015",
                    "13"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "38422",
                    "Jessica Jones Season 2",
                    "Drowning in anger, Jessica Jones is forced to reckon with her past, her powers and her newfound fame as she dishes out her own messy form of justice. Finally ready to face her past, Jessica hunts down the source of her powers and uncovers a link to a shadowy killer who's terrorizing the city.",
                    "88",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/7pJP2YLAz1fcyqk0VlvcvxsyUCm.jpg",
                    "2018",
                    "13"
                )
            )
            tvShows.add(
                TvShowResponse(
                    "38432",
                    "Jessica Jones Season 3",
                    "In the final season, Jessica matches wits with a calculating serial killer, and a newly powered Trish goes to extremes to stamp out evil.",
                    "89",
                    "Action",
                    "https://image.tmdb.org/t/p/w1280/5HyBZrWyZVTTClijgk4ivITDMe.jpg",
                    "2019",
                    "13"
                )
            )

            return tvShows
        }
    }
}