package com.naufalprakoso.moviesjetpack.ui.utils

import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.stubbing.Answer
import androidx.paging.PagedList
import org.mockito.Mockito.mock

object PagedListUtil {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        val answer: Answer<T> = Answer { invocation ->
            val index = invocation.arguments[0] as Int
            list[index]
        }
        `when`(pagedList[anyInt()]).thenAnswer(answer)
        `when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }
}