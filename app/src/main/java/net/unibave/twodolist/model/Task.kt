package net.unibave.twodolist.model

import java.util.Date;

data class Task(var id: String, var name: String, var createdAt: Date, var done: Boolean)