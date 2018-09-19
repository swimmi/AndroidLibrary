package net.swimmi.androidlibrary.model

data class Treasure(var id: Int, var title: String, var desc: String, var tag: String?, var datetime: String) {

    var tags: List<String>? = null
        get()  {
                if (tag != null)
                    return tag!!.split(" ")
                return null
            }
}