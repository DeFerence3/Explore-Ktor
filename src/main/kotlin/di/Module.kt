package di

import com.diffy.database.service.ApplicationBusinessService
import com.diffy.database.service.BusinessActionService
import database.service.RoleService
import org.koin.dsl.module
import database.service.UserService

val appModule = module {
    single { UserService() }
    single { RoleService() }
    single { ApplicationBusinessService() }
    single { BusinessActionService() }
}