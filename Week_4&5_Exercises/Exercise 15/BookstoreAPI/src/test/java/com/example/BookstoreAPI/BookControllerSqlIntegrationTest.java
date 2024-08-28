package com.example.BookstoreAPI;

@Sql({"/test-schema.sql", "/test-data.sql"})
public class BookControllerSqlIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetBookById() throws Exception {
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"Effective Java\"}"));
    }

}
