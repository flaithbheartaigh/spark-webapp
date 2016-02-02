<#import "masterTemplate.ftl" as layout/>

<@layout.masterTemplate>
<div>
    <h2>${message}</h2>
    <ul>
    <#list books as book>
        <li>${book.title} by ${book.author} <a href="/books/delete/${book.bookID}">delete</a> </li>
    </#list>
    </ul>
    <a href="/books/new">new</a>
</div>
</@layout.masterTemplate>