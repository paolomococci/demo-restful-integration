/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed following in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.demo.config

import local.example.demo.model.Vine
import local.example.demo.repository.VineRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VineItemsInitializer {

    @Bean
    internal fun init(vineRepository: VineRepository) = CommandLineRunner {
        vineRepository.save(Vine(name = "Sagrantino", color = "red"))
        vineRepository.save(Vine(name = "Sangiovese", color = "red"))
        vineRepository.save(Vine(name = "Ciliegiolo", color = "red"))
        vineRepository.save(Vine(name = "Pecorino", color = "white"))
        vineRepository.save(Vine(name = "Falanghina"))
    }
}
