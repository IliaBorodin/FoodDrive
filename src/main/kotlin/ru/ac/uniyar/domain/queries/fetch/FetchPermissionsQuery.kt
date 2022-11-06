package ru.ac.uniyar.domain.queries.fetch

import ru.ac.uniyar.domain.storage.rolePermissions.RolePermissionsRepository
import java.util.*

class FetchPermissionsQuery(private val permissionsRepository: RolePermissionsRepository,){
    operator fun invoke(roleId: UUID) = permissionsRepository.fetch(roleId)
}