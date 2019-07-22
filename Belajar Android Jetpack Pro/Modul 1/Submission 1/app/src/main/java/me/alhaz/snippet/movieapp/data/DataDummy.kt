package me.alhaz.snippet.movieapp.data

import android.content.Context
import com.google.gson.Gson
import me.alhaz.snippet.movieapp.models.Movie
import me.alhaz.snippet.movieapp.models.TVShow

object DataDummy {

    fun generateMovies(context: Context): ArrayList<Movie> {
        var movies = ArrayList<Movie>()
        val jsonfile: String = context.assets.open("movies.json").bufferedReader().use {
            it.readText()
        }
        var gson = Gson()
        gson?.let {
            val jsonMovies: List<Movie> = it.fromJson(jsonfile , Array<Movie>::class.java).toList()
            movies.addAll(jsonMovies)
        }
        return movies
    }

    fun listMovies(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(Movie(1,
            "A Star Is Born",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            2018,
            75,
            "1h 59m",
            "poster_a_start_is_born"))
        movies.add(Movie(2,
            "Alita: Battle Angle",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            2019,
            68,
            "1h 59m",
            "poster_alita"))
        movies.add(Movie(3,
            "Aquaman",
            "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
            2018,
            68,
            "1h 59m",
            "poster_aquaman"))
        movies.add(Movie(4,
            "Bohemian Rhapsody",
            "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
            2018,
            81,
            "1h 59m",
            "poster_bohemian"))
        movies.add(Movie(5,
            "Cold Pursuit",
            "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
            2019,
            54,
            "1h 59m",
            "poster_cold_persuit"))
        movies.add(Movie(6,
            "Fantastic Beasts: The Crimes of Grindelwald",
            "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
            2018,
            69,
            "2h 14m",
            "poster_crimes"))
        movies.add(Movie(7,
            "Glass",
            "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
            2019,
            65,
            "2h 9m",
            "poster_glass"))
        movies.add(Movie(8,
            "How to Train Your Dragon: The Hidden World",
            "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            2019,
            76,
            "1h 44m",
            "poster_how_to_train"))
        movies.add(Movie(9,
            "Avengers: Infinity War",
            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
            2018,
            83,
            "1h 44m",
            "poster_infinity_war"))
        movies.add(Movie(10,
            "Mary Queen of Scots",
            "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
            2018,
            66,
            "2h 4m",
            "poster_marry_queen"))
        movies.add(Movie(11,
            "Master Z: Ip Man Legacy",
            "While keeping a low profile after his defeat by Ip Man, Cheung Tin Chi gets into trouble after getting in a fight with a powerful foreigner.",
            2018,
            52,
            "1h 47m",
            "poster_master_z"))
        movies.add(Movie(12,
            "Mortal Engines",
            "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
            2018,
            60,
            "2h 9m",
            "poster_mortal_engines"))
        movies.add(Movie(13,
            "Overlord",
            "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
            2018,
            66,
            "1h 50m",
            "poster_overlord"))
        movies.add(Movie(14,
            "Ralph Breaks the Internet",
            "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            2018,
            72,
            "1h 52m",
            "poster_ralph"))
        movies.add(Movie(15,
            "Robin Hood",
            "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
            2018,
            58,
            "1h 56m",
            "poster_robin_hood"))
        movies.add(Movie(16,
            "Serenity",
            "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.",
            2019,
            51,
            "1h 46m",
            "poster_serenity"))
        movies.add(Movie(17,
            "Spider-Man: Into the Spider-Verse",
            "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
            2018,
            84,
            "1h 57m",
            "poster_spiderman"))
        movies.add(Movie(18,
            "T-34",
            "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
            2018,
            49,
            "2h 19m",
            "poster_t34"))
        return movies
    }

    fun generateTVShows(context: Context): ArrayList<TVShow> {
        var tvShows = ArrayList<TVShow>()
        val jsonfile: String = context.assets.open("tvshows.json").bufferedReader().use {
            it.readText()
        }
        var gson = Gson()
        gson?.let {
            val jsonShows: List<TVShow> = it.fromJson(jsonfile , Array<TVShow>::class.java).toList()
            tvShows.addAll(jsonShows)
        }
        return tvShows
    }

    fun listTVShows(): ArrayList<TVShow> {
        val tvShows = ArrayList<TVShow>()
        tvShows.add(
            TVShow(1,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.\n\n",
                2012,
                58,
                "42m",
                "poster_arrow"
            )
        )
        tvShows.add(
            TVShow(2,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                2019,
                62,
                "60m",
                "poster_doom_patrol"
            )
        )
        tvShows.add(
            TVShow(3,
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure.\n\n",
                1986,
                71,
                "25m",
                "poster_dragon_ball"
            )
        )
        tvShows.add(
            TVShow(4,
                "Fairy Tail",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                2009,
                64,
                "25m",
                "poster_fairytail"
            )
        )
        tvShows.add(
            TVShow(5,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                1999,
                65,
                "22m",
                "poster_family_guy"
            )
        )
        tvShows.add(
            TVShow(6,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.\n\n",
                2014,
                67,
                "44m",
                "poster_flash"
            )
        )
        tvShows.add(
            TVShow(7,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                2011,
                81,
                "60m",
                "poster_god"
            )
        )
        tvShows.add(
            TVShow(8,
                "Gotham",
                "Before there was Batman, there was GOTHAM.\n\nEveryone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                2014,
                68,
                "43m",
                "poster_gotham"
            )
        )
        tvShows.add(
            TVShow(9,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                2005,
                63,
                "43m",
                "poster_grey_anatomy"
            )
        )
        tvShows.add(
            TVShow(10,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                2019,
                64,
                "50m",
                "poster_hanna"
            )
        )
        tvShows.add(
            TVShow(11,
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                2017,
                61,
                "55m",
                "poster_iron_fist"
            )
        )
        tvShows.add(
            TVShow(12,
                "Naruto Shippūden",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                2007,
                75,
                "22m",
                "poster_naruto_shipudden"
            )
        )
        tvShows.add(
            TVShow(13,
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                2003,
                67,
                "45m",
                "poster_ncis"
            )
        )
        tvShows.add(
            TVShow(14,
                "Riverdate",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                2016,
                69,
                "45m",
                "poster_riverdale"
            )
        )
        tvShows.add(
            TVShow(15,
                "Shameless",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                2011,
                78,
                "55m",
                "poster_shameless"
            )
        )
        tvShows.add(
            TVShow(16,
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.\n\n",
                2015,
                58,
                "42m",
                "poster_supergirl"
            )
        )
        tvShows.add(
            TVShow(17,
                "American Super Natural",
                "Small town Americana has been haunted for hundreds of years by monsters that weather created and folklore fostered.",
                2014,
                80,
                "40m",
                "poster_supernatural"
            )
        )
        tvShows.add(
            TVShow(18,
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.\n\n",
                1989,
                71,
                "22m",
                "poster_the_simpson"
            )
        )
        tvShows.add(
            TVShow(19,
                "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                2019,
                76,
                "60m",
                "poster_the_umbrella"
            )
        )
        tvShows.add(
            TVShow(20,
                "The Umbrella Academy",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                2010,
                73,
                "42m",
                "poster_the_walking_dead"
            )
        )
        return tvShows
    }
}