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

package local.example.demo.repository

import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class VineRepositoryMockMvcTests {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Autowired
    private val vineRepository: VineRepository? = null

    @Test
    @Throws(Exception::class)
    fun `verify existence`() {
        mockMvc!!.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$._links.vines").exists())
    }

    @Test
    @Throws(Exception::class)
    fun `create test`() {
        mockMvc!!.perform(post("/vines").content(SAMPLE))
                .andExpect(status().isCreated)
                .andExpect(header().string("Location", containsString("vines/")))
    }

    @Test
    @Throws(Exception::class)
    fun `read test`() {
        val mvcResult = mockMvc!!.perform(post("/vines").content(SAMPLE))
                .andExpect(status().isCreated)
                .andReturn()
        val result = mvcResult.response.getHeader("Location")
        mockMvc.perform(get(result!!))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Sagrantino"))
    }

    @Test
    @Throws(Exception::class)
    fun `update test`() {
        val mvcResult = mockMvc!!.perform(post("/vines").content(SAMPLE))
                .andExpect(status().isCreated)
                .andReturn()
        val result = mvcResult.response.getHeader("Location")
        mockMvc.perform(patch(result!!).content("{\"name\":\"Sagrantino\"}"))
                .andExpect(status().isNoContent)
        mockMvc.perform(get(result))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value("Sagrantino"))
    }

    @Test
    @Throws(Exception::class)
    fun `delete test`() {
        val mvcResult = mockMvc!!.perform(post("/vines").content(SAMPLE))
                .andExpect(status().isCreated)
                .andReturn()
        val result = mvcResult.response.getHeader("Location")
        mockMvc.perform(delete(result!!)).andExpect(status().isNoContent)
        mockMvc.perform(get(result)).andExpect(status().isNotFound)
    }

    companion object {
        private const val SAMPLE: String = "{\"name\":\"Ciliegiolo\"}"
    }
}
