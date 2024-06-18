package com.example.pwa_backend_api.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pwa_backend_api.dtos.RegisterDTO;
import com.example.pwa_backend_api.entities.User;
import com.example.pwa_backend_api.repositories.UserRepo;

@Service
public class AuthService implements UserDetailsService {
    UserRepo userRepo;

    public ResponseEntity<String> register(RegisterDTO data){

        if(userRepo.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        String encondedPassword = new BCryptPasswordEncoder().encode(data.password());

        JSONArray jsonArray = null;
        User newUser = new User(data.getName(), data.getEmail(), encondedPassword);
        JSONObject jsonObjectUser = new JSONObject();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/static/users.json", StandardCharsets.UTF_8))) {
            String jsonString = bufferedReader.lines().collect(Collectors.joining());
            if (!jsonString.isEmpty()) {
                jsonArray = new JSONArray(jsonString);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Não foi possível abrir o arquivo JSON");
            return null;
        }
        int newId = jsonArray.length() + 1;
        newUser.setId(String.valueOf(newId));
        
        jsonObjectUser.put("id", newUser.getId());
        jsonObjectUser.put("email", newUser.getEmail());
        jsonObjectUser.put("name", newUser.getName());
        jsonObjectUser.put("password", newUser.getPassword());
        jsonObjectUser.put("forms:", newUser.getForms());
        jsonObjectUser.put("created_at", newUser.getCreated_at());
        jsonObjectUser.put("updated_at", newUser.getUpdated_at());

        jsonArray.put(jsonObjectUser);
        
        try(FileWriter fileWriter = new FileWriter("src/main/resources/static/users.json", StandardCharsets.UTF_8)){
            fileWriter.write(jsonArray.toString(4));
        } catch(IOException e){
            e.printStackTrace();
            System.err.println("Não foi possível adicionar user");
            return null;
        }
        this.userRepo.save(newUser);
        return ResponseEntity.ok().build();
    }

        public String listUsers() {
            String jsonString = null;
            JSONArray jsonArray = null;

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/static/users.json", StandardCharsets.UTF_8))) {
                jsonString = bufferedReader.lines().collect(Collectors.joining());
                jsonArray = new JSONArray(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Não foi possível abrir o arquivo JSON");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao processar o JSON");
                return null;
            }

            return jsonArray.toString();
        }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                //return userRepo.findByEmail(email);
                return null;
        }

}
