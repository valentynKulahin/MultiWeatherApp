package com.example.domain.usecase

import com.example.domain.model.news.Article
import com.example.domain.model.news.NewsResult
import com.example.domain.model.news.Source
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsUseCase @Inject constructor() {

    operator fun invoke(): NewsResult {
        return NewsResult(
            status = "ok",
            totalResults = 37,
            articles = listOf(
                Article(
                    source = Source(
                        id = null,
                        name = "Yahoo Entertainment"
                    ),
                    author = "Jason Owens",
                    title = "Joe Burrow good enough, but calf concerns persist in Bengals win over Rams - Yahoo Sports",
                    description = "The Bengals picked up a desperately needed win while their offense continued to struggle with an ailing Joe Burrow.",
                    url = "https://sports.yahoo.com/bengals-joe-burrow-win-rams-calf-concerns-121318859.html",
                    urlToImage = "https://s.yimg.com/ny/api/res/1.2/VhQbznTzzYrQU1U4oK9D3w--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://s.yimg.com/os/creatr-uploaded-images/2023-09/7cfb8b80-5c16-11ee-b95e-7937fb858d20",
                    publishedAt = "2023-09-26T12:13:18Z",
                    content = "Joe Burrow was far from his best Monday night.\r\nBut he was good enough for a Cincinnati Bengals team that rolled the dice on starting its ailing Pro Bowl quarterback. While Cincinnati's defense stifl… [+3667 chars]"
                ),
                Article(
                    source = Source(
                        id = "cbs-news",
                        name = "CBS News"
                    ),
                    author = "Dr. Céline Gounder, Alexander Tin",
                    title = "How to get the new COVID vaccine for free, with or without insurance - CBS News",
                    description = "Americans still have ways to get vaccinated against COVID-19 at no out-of-pocket cost. Here's what to know.",
                    url = "https://www.cbsnews.com/news/new-covid-vaccine-free-with-without-insurance/",
                    urlToImage = "https://assets3.cbsnewsstatic.com/hub/i/r/2023/09/12/c24f5f8a-7285-4bee-a9f5-df3ea7f965ae/thumbnail/1200x630g2/78081cf1ace05dd6f4c6ef0712dc4a65/2023-08-19t120054z-1954685218-rc27jt97bx8b-rtrmadp-3-health-coronavirus-variants.jpg?v=f5251b37272e6b1bc4e5456ab4445a67",
                    publishedAt = "2023-09-26T11:42:00Z",
                    content = "Federal health authorities are urging Americans to reach out to their insurers after reports of some people encountering trouble getting their new COVID-19 vaccine shot for free. Those issues have ar… [+5464 chars]"
                )
            )
        )
    }

}