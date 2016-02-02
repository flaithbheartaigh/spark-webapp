<#import "masterTemplate.ftl" as layout/>

<@layout.masterTemplate>
<!-- Seperate Script layout?? -->
<script language="JavaScript" src="/scripts/spi-test.js"></script>

<div>
    <h2>${message}</h2>
    <ul id="booksholder">
    <#list books as book>
        <li data-id="${book.bookID}">${book.title} by ${book.author} <a href="#" onclick="page.delete(this);">delete</a> </li>
    </#list>
    </ul>
    <a href="#" onclick="page.new();">new</a>
</div>
</@layout.masterTemplate>