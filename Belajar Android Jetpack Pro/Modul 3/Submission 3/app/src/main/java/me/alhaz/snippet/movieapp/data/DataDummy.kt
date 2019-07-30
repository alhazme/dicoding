package me.alhaz.snippet.movieapp.data

import me.alhaz.snippet.movieapp.repositories.movies.local.entities.Movie
import me.alhaz.snippet.movieapp.repositories.tvshows.local.entities.TVShow

object DataDummy {

    fun generateListMovie(): ArrayList<Movie> {

        val movies = ArrayList<Movie>()

        // 1
        movies.add(
            Movie(
                id = 420818,
                title = "The Lion King",
                voteAverage = 7.1,
                overview = "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
                releaseDate = "2019-07-12",
                runtime = 118,
                posterPath = "/dzBtMocZuJbjLOXvrl4zGYigDzh.jpg"
            )
        )

        // 2
        movies.add(
            Movie(
                id = 566555,
                title = "Detective Conan: The Fist of Blue Sapphire",
                voteAverage = 5.0,
                overview = "23rd movie in the \"Detective Conan\" franchise.",
                releaseDate = "2019-04-12",
                runtime = 95,
                posterPath = "/86Y6qM8zTn3PFVfCm9J98Ph7JEB.jpg"
            )
        )

        // 3
        movies.add(
            Movie(
                id = 429617,
                title = "Spider-Man: Far from Home",
                voteAverage = 7.8,
                overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                releaseDate = "2019-06-28",
                runtime = 129,
                posterPath = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg"
            )
        )

        // 4
        movies.add(
            Movie(
                id = 613473,
                title = "Burglary",
                voteAverage = 3.0,
                overview = "While playing videogames Mike spots a mysterious masked man outside his building.",
                releaseDate = "2019-07-19",
                runtime = 6,
                posterPath = "/xztkk3qwvjTfKArjTkhWUCuadFY.jpg"
            )
        )

        // 5
        movies.add(
            Movie(
                id = 447404,
                title = "Pokémon Detective Pikachu",
                voteAverage = 7.0,
                overview = "In a world where people collect pocket-size monsters (Pokémon) to do battle, a boy comes across an intelligent monster who seeks to be a detective.",
                releaseDate = "2019-05-03",
                runtime = 105,
                posterPath = "/wgQ7APnFpf1TuviKHXeEe3KnsTV.jpg"
            )
        )

        // 6
        movies.add(
            Movie(
                id = 399579,
                title = "Alita: Battle Angel",
                voteAverage = 6.8,
                overview = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                releaseDate = "2019-01-31",
                runtime = 119,
                posterPath = "/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )

        // 7
        movies.add(
            Movie(
                id = 301528,
                title = "Toy Story 4",
                voteAverage = 7.7,
                overview = "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
                releaseDate = "2019-06-19",
                runtime = 100,
                posterPath = "/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg"
            )
        )

        // 8
        movies.add(
            Movie(
                id = 486589,
                title = "Red Shoes and the Seven Dwarfs",
                voteAverage = 8.0,
                overview = "Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.",
                releaseDate = "2019-07-25",
                runtime = 0,
                posterPath = "/xQccIXfq9J4tgbvdSSPPLLYZGRD.jpg"
            )
        )

        // 9
        movies.add(
            Movie(
                id = 299537,
                title = "Captain Marvel",
                voteAverage = 7.8,
                overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                releaseDate = "2019-06-28",
                runtime = 124,
                posterPath = "/rjbNpRMoVvqHmhmksbokcyCr7wn.jpg"
            )
        )

        // 10
        movies.add(
            Movie(
                id = 479455,
                title = "Men in Black: International",
                voteAverage = 5.9,
                overview = "The Men in Black have always protected the Earth from the scum of the universe. In this new adventure, they tackle their biggest, most global threat to date: a mole in the Men in Black organization.",
                releaseDate = "2019-06-12",
                runtime = 115,
                posterPath = "/dPrUPFcgLfNbmDL8V69vcrTyEfb.jpg"
            )
        )

        // 11
        movies.add(
            Movie(
                id = 299534,
                title = "Avenger: Endgame",
                voteAverage = 8.4,
                overview = "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
                releaseDate = "2019-02-24",
                runtime = 181,
                posterPath = "/or06FN3Dka5tukK1e9sl16pB3iy.jpg"
            )
        )

        // 12
        movies.add(
            Movie(
                id = 920,
                title = "Cars",
                voteAverage = 6.7,
                overview = "Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters.",
                releaseDate = "2006-06-08",
                runtime = 117,
                posterPath = "/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg"
            )
        )

        // 13
        movies.add(
            Movie(
                id = 287947,
                title = "Shazam!",
                voteAverage = 7.1,
                overview = "A boy is given the ability to become an adult superhero in times of need with a single magic word.",
                releaseDate = "2019-03-23",
                runtime = 132,
                posterPath = "/xnopI5Xtky18MPhK40cZAGAOVeV.jpg"
            )
        )

        // 14
        movies.add(
            Movie(
                id = 459992,
                title = "Long Shot",
                voteAverage = 6.9,
                overview = "When Fred Flarsky reunites with and charms his first crush, Charlotte Field—one of the most influential women in the world. As Charlotte prepares to make a run for the Presidency, she hires Fred as her speechwriter and sparks fly.",
                releaseDate = "2019-05-02",
                runtime = 125,
                posterPath = "/m2ttWZ8rMRwIMT7zA48Jo6mTkDS.jpg"
            )
        )

        // 15
        movies.add(
            Movie(
                id = 456740,
                title = "Hellboy",
                voteAverage = 5.0,
                overview = "Hellboy comes to England, where he must defeat Nimue, Merlin's consort and the Blood Queen. But their battle will bring about the end of the world, a fate he desperately tries to turn away.",
                releaseDate = "2019-04-10",
                runtime = 121,
                posterPath = "/bk8LyaMqUtaQ9hUShuvFznQYQKR.jpg"
            )
        )

        // 16
        movies.add(
            Movie(
                id = 466272,
                title = "Once Upon a Time in Hollywood",
                voteAverage = 8.0,
                overview = "A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood's Golden Age in 1969 Los Angeles.",
                releaseDate = "2019-07-25",
                runtime = 161,
                posterPath = "/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg"
            )
        )

        // 17
        movies.add(
            Movie(
                id = 458156,
                title = "John Wick: Chapter 3 – Parabellum",
                voteAverage = 7.1,
                overview = "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
                releaseDate = "2019-05-15",
                runtime = 131,
                posterPath = "/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg"
            )
        )

        // 18
        movies.add(
            Movie(
                id = 553100,
                title = "Wild and Free",
                voteAverage = 6.0,
                overview = "Ellie and Jake fall in love, but struggle with their relationship when they discover an unexpected connection between their pasts.",
                releaseDate = "2018-10-10",
                runtime = 106,
                posterPath = "/jLGNqaymD0ygyhafhv5fM3nXcge.jpg"
            )
        )

        // 19
        movies.add(
            Movie(
                id = 511987,
                title = "Crawl",
                voteAverage = 5.9,
                overview = "While struggling to save her father during a Category 5 hurricane, a young woman finds herself trapped inside a flooding house and fighting for her life against Florida’s most savage and feared predators.",
                releaseDate = "2019-07-11",
                runtime = 87,
                posterPath = "/mKxpYRIrCZLxZjNqpocJ2RdQW8v.jpg"
            )
        )

        // 20
        movies.add(
            Movie(
                id = 299536,
                title = "Avengers: Infinity War",
                voteAverage = 8.3,
                overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                releaseDate = "2018-04-25",
                runtime = 149,
                posterPath = "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )

        return movies
    }

    fun generateTVShows(): ArrayList<TVShow> {

        val tvShows = ArrayList<TVShow>()

        // 1

        tvShows.add(
            TVShow(
                id = 67195,
                name = "Legion",
                voteAverage = 7.5,
                overview = "David Haller, AKA Legion, is a troubled young man who may be more than human. Diagnosed as schizophrenic, David has been in and out of psychiatric hospitals for years. But after a strange encounter with a fellow patient, he’s confronted with the possibility that the voices he hears and the visions he sees might be real.",
                firstAirDate = "2017-02-08",
                numberOfEpisodes = 29,
                posterPath = "/vT0Zsbm4GWd7llNjgWEtwY0CqOv.jpg"
            )
        )

        // 2
        tvShows.add(
            TVShow(
                id = 60735,
                name = "The Flash",
                voteAverage = 6.7,
                overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                firstAirDate = "2014-10-07",
                numberOfEpisodes = 115,
                posterPath = "/fki3kBlwJzFp8QohL43g9ReV455.jpg"
            )
        )

        // 3
        tvShows.add(
            TVShow(
                id = 71446,
                name = "Money Heist",
                voteAverage = 8.1,
                overview = "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing.",
                firstAirDate = "2017-05-02",
                numberOfEpisodes = 23,
                posterPath = "/MoEKaPFHABtA1xKoOteirGaHl1.jpg"
            )
        )

        // 4
        tvShows.add(
            TVShow(
                id = 1412,
                name = "Arrow",
                voteAverage = 5.8,
                overview = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                firstAirDate = "2014-10-07",
                numberOfEpisodes = 162,
                posterPath = "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg"
            )
        )

        // 5
        tvShows.add(
            TVShow(
                id = 86816,
                name = "Crímenes que cambiaron la historia",
                voteAverage = 3.5,
                overview = "",
                firstAirDate = "2019-01-22",
                numberOfEpisodes = 6,
                posterPath = "/fMgPDXqSL4XILb0RKSXpDqc5tJ9.jpg"
            )
        )

        // 6
        tvShows.add(
            TVShow(
                id = 90670,
                name = "Pandora",
                voteAverage = 8.3,
                overview = "Set in the year 2199, a young woman who has lost everything finds a new life at Earth's Space Training Academy where she learns to defend the galaxy from intergalactic threats.",
                firstAirDate = "2019-07-16",
                numberOfEpisodes = 4,
                posterPath = "/tPsvhL45f1AjES5rycFIxnbaH8v.jpg"
            )
        )

        // 7
        tvShows.add(
            TVShow(
                id = 66732,
                name = "Stranger Things",
                voteAverage = 8.3,
                overview = "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
                firstAirDate = "2016-07-15",
                numberOfEpisodes = 25,
                posterPath = "/x2LSRK2Cm7MZhjluni1msVJ3wDF.jpg"
            )
        )

        // 8
        tvShows.add(
            TVShow(
                id = 48866,
                name = "The 100",
                voteAverage = 6.5,
                overview = "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                firstAirDate = "2014-03-19",
                numberOfEpisodes = 84,
                posterPath = "/wBzNjurA8ijJPF21Ggs9nbviIzi.jpg"
            )
        )

        // 9
        tvShows.add(
            TVShow(
                id = 1416,
                name = "Grey's Anatomy",
                voteAverage = 6.3,
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                firstAirDate = "2005-03-27",
                numberOfEpisodes = 341,
                posterPath = "/eqgIOObafPJitt8JNh1LuO2fvqu.jpg"
            )
        )

        // 10
        tvShows.add(
            TVShow(
                id = 1434,
                name = "Family Guy",
                voteAverage = 6.8,
                overview = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                firstAirDate = "1999-01-31",
                numberOfEpisodes = 328,
                posterPath = "/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg"
            )
        )

        // 11
        tvShows.add(
            TVShow(
                id = 484,
                name = "Murder, She Wrote",
                voteAverage = 7.1,
                overview = "An unassuming mystery writer turned sleuth uses her professional insight to help solve real-life homicide cases.",
                firstAirDate = "1984-09-30",
                numberOfEpisodes = 264,
                posterPath = "/j6DC2Xe0SZS6xKS2d6LWL1EGwzX.jpg"
            )
        )

        // 12
        tvShows.add(
            TVShow(
                id = 63926,
                name = "One-Punch Man",
                voteAverage = 8.0,
                overview = "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?",
                firstAirDate = "2005-08-23",
                numberOfEpisodes = 69,
                posterPath = "/iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg"
            )
        )

        // 13
        tvShows.add(
            TVShow(
                id = 62286,
                name = "Fear the Walking Dead",
                voteAverage = 6.3,
                overview = "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                firstAirDate = "2005-08-23",
                numberOfEpisodes = 69,
                posterPath = "/aOdTWn8dXlS0tA5xl0ZBr8Ws15R.jpg"
            )
        )

        // 14
        tvShows.add(
            TVShow(
                id = 456,
                name = "The Simpsons",
                voteAverage = 7.1,
                overview = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                firstAirDate = "1989-12-17",
                numberOfEpisodes = 662,
                posterPath = "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg"
            )
        )

        // 15
        tvShows.add(
            TVShow(
                id = 86034,
                name = "Arifureta: From Commonplace to World's Strongest",
                voteAverage = 5.3,
                overview = "Seventeen-year-old Hajime Nagumo is your average, everyday otaku. However, his simple life of pulling all-nighters and sleeping in school is suddenly turned upside down when he, along with the rest of his class, is summoned to a fantasy world! They're treated like heroes and tasked with the duty of saving the human race from utter extinction. But what should have been any otaku's wet dream quickly turns into Hajime's nightmare. While the rest of his class are blessed with godlike powers, Hajime's job, Synergist, only has a single transmutation skill. Ridiculed and bullied by his classmates for being weak, he soon finds himself in despair. Will he be able to survive in this dangerous world of monsters and demons with only a glorified blacksmith's level of strength?",
                firstAirDate = "2019-07-08",
                numberOfEpisodes = 12,
                posterPath = "/cmMh8awgtRvLUKmYvIXtVCYINIv.jpg"
            )
        )

        // 16
        tvShows.add(
            TVShow(
                id = 1403,
                name = "Marvel's Agents of S.H.I.E.L.D.",
                voteAverage = 6.8,
                overview = "Agent Phil Coulson of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement and Logistics Division) puts together a team of agents to investigate the new, the strange and the unknown around the globe, protecting the ordinary from the extraordinary.",
                firstAirDate = "2013-09-24",
                numberOfEpisodes = 121,
                posterPath = "/cXiETfFK1BTLest5fhTLfDLRdL6.jpg"
            )
        )

        // 17
        tvShows.add(
            TVShow(
                id = 86031,
                name = "Dr. Stone",
                voteAverage = 6.3,
                overview = "The science-fiction adventure follows two boys struggle to revive humanity after a mysterious crisis has left everyone in the world turned to stone for several millennia.",
                firstAirDate = "2019-07-05",
                numberOfEpisodes = 24,
                posterPath = "/dLlnzbDCblBXcJqFLXyvN43NIwp.jpg"
            )
        )

        // 18
        tvShows.add(
            TVShow(
                id = 1622,
                name = "Supernatural",
                voteAverage = 7.3,
                overview = "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
                firstAirDate = "2005-09-13",
                numberOfEpisodes = 307,
                posterPath = "/3iFm6Kz7iYoFaEcj4fLyZHAmTQA.jpg"
            )
        )

        // 19
        tvShows.add(
            TVShow(
                id = 60708,
                name = "Gotham",
                voteAverage = 6.8,
                overview = "Before there was Batman, there was GOTHAM.\n\nEveryone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker? ",
                firstAirDate = "2014-09-22",
                numberOfEpisodes = 100,
                posterPath = "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
            )
        )

        // 20
        tvShows.add(
            TVShow(
                id = 90215,
                name = "Isekai Cheat Magician",
                voteAverage = 6.8,
                overview = "As regular high school students Taichi and Rin disappeared in a beam of light. When they came to, the two of them were already in a world of swords and magic. Finally getting away after experiencing an attack by monsters, following the suggestion of adventurers they headed on the path towards the guild. In the guild, the two of them found out that they possessed unbelievably powerful magic. Thus the regular high school students transformed into the strongest cheats...",
                firstAirDate = "2019-07-10",
                numberOfEpisodes = 12,
                posterPath = "/bda9my9vSL78zO40hcHLzb0Ae4t.jpg"
            )
        )

        return tvShows
    }

}