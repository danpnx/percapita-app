package br.com.percapita.payload

import br.com.percapita.model.Tag
import kotlinx.serialization.Serializable

@Serializable
class TagList(val list: List<Tag>)