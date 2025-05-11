package com.example.eunoia.common.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

enum class TokenType {
    BOLD,
    ITALIC,
    LINK
}

data class MarkdownToken(
    val type: TokenType,
    val start: Int,
    val end: Int,
    val groups: List<String>
)

object MarkdownHelper {
    fun parseMarkdownToAnnotatedString(markdown: String): AnnotatedString {
        val tokens = collectTokens(markdown)
        return buildAnnotatedString(markdown, tokens)
    }

    private fun collectTokens(markdown: String): List<MarkdownToken> {
        val tokens = mutableListOf<MarkdownToken>()

        val boldRegex = """\*\*(.+?)\*\*""".toRegex()
        boldRegex.findAll(markdown).forEach { matchResult ->
            tokens.add(
                MarkdownToken(
                    type = TokenType.BOLD,
                    start = matchResult.range.first,
                    end = matchResult.range.last + 1,
                    groups = matchResult.groupValues.drop(1)
                )
            )
        }

        val italicRegex = """\*(.+?)\*""".toRegex()
        italicRegex.findAll(markdown).forEach { matchResult ->
            tokens.add(
                MarkdownToken(
                    type = TokenType.ITALIC,
                    start = matchResult.range.first,
                    end = matchResult.range.last + 1,
                    groups = matchResult.groupValues.drop(1)
                )
            )
        }

        val linkRegex = """\[(.+?)]\((.+?)\)""".toRegex()
        linkRegex.findAll(markdown).forEach { matchResult ->
            tokens.add(
                MarkdownToken(
                    type = TokenType.LINK,
                    start = matchResult.range.first,
                    end = matchResult.range.last + 1,
                    groups = matchResult.groupValues.drop(1) // group 0: link text, group 1: url
                )
            )
        }

        tokens.sortBy { it.start }
        return tokens
    }

    private fun buildAnnotatedString(
        markdown: String,
        tokens: List<MarkdownToken>
    ): AnnotatedString {
        val builder = AnnotatedString.Builder()
        var currentIndex = 0
        for (token in tokens) {
            if (token.start < currentIndex) continue

            builder.append(markdown.substring(currentIndex, token.start))

            when (token.type) {
                TokenType.BOLD -> {
                    val content = token.groups.firstOrNull() ?: ""
                    appendBold(builder, content)
                }

                TokenType.ITALIC -> {
                    val content = token.groups.firstOrNull() ?: ""
                    appendItalic(builder, content)
                }

                TokenType.LINK -> {
                    val linkText = token.groups.getOrNull(0) ?: ""
                    val url = token.groups.getOrNull(1) ?: ""
                    appendLink(builder, linkText, url)
                }
            }
            currentIndex = token.end
        }
        if (currentIndex < markdown.length) {
            builder.append(markdown.substring(currentIndex))
        }
        return builder.toAnnotatedString()
    }

    private fun appendBold(builder: AnnotatedString.Builder, text: String) {
        val start = builder.length
        builder.append(text)
        builder.addStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold),
            start = start,
            end = builder.length
        )
    }

    private fun appendItalic(builder: AnnotatedString.Builder, text: String) {
        val start = builder.length
        builder.append(text)
        builder.addStyle(
            style = SpanStyle(fontStyle = FontStyle.Italic),
            start = start,
            end = builder.length
        )
    }

    private fun appendLink(builder: AnnotatedString.Builder, text: String, url: String) {
        val start = builder.length
        builder.append(text)
        builder.addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = start,
            end = builder.length
        )
        builder.addStringAnnotation(
            tag = "URL",
            annotation = url,
            start = start,
            end = builder.length
        )
    }

}