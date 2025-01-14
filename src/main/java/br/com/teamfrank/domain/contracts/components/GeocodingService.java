package br.com.teamfrank.domain.contracts.components;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.teamfrank.domain.models.entities.Coordenada;

@Service
public class GeocodingService {

    private static final String USER_AGENT = "TeamFrankApp/1.0 (contato@teamfrank.com)";

    public Coordenada buscarCoordenadas(String endereco) {
        try {
            String enderecoFormatado = URLEncoder.encode(endereco, "UTF-8");
            String url = "https://nominatim.openstreetmap.org/search?q=" + enderecoFormatado + "&format=json&limit=1";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "TeamFrankApp/1.0 (contato@teamfrank.com)")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("🌐 Resposta da API: " + response.body());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.body());

            if (root.isArray() && root.size() > 0) {
                JsonNode location = root.get(0);
                String latitude = location.get("lat").asText();
                String longitude = location.get("lon").asText();
                System.out.println("📍 Coordenadas encontradas: " + latitude + ", " + longitude);

                return new Coordenada(latitude, longitude);
            } else {
                System.out.println("⚠️ Nenhuma coordenada encontrada.");
                return null;
            }

        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar coordenadas: " + e.getMessage());
            return null;
        }
    }
}
