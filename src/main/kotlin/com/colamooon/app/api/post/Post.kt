package com.colamooon.app.api.post


import com.colamooon.app.api.common.model.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "posts")
class Post : BaseEntity() {

    @NotEmpty
    var title = ""
    var content = ""


}
