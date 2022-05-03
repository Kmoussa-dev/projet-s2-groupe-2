package projet.group2.gestionEmargement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import projet.group2.gestionEmargement.entity.Utilisateur;
import projet.group2.gestionEmargement.service.UtilisateurService;

import java.awt.image.BufferedImage;
import java.util.Objects;

@Configuration
public class SecuriteConfiguration extends WebSecurityConfigurerAdapter {


    private final UtilisateurService utilisateurService;

    public SecuriteConfiguration( UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }





    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/emergement/etudiant").permitAll()
                .antMatchers(HttpMethod.GET,"/api/emergement/etudiants").permitAll()
                .antMatchers(HttpMethod.GET,"/api/emergement/enseignants").hasRole("PROF")
                .antMatchers(HttpMethod.PUT,"/api/emergement/seance/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/emergement/seance").permitAll()
                .antMatchers(HttpMethod.GET,"/api/emergement/seance/token**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return login -> {
            Utilisateur utilisateur = utilisateurService.getUtilisateur(login);

            if (Objects.isNull(utilisateur)){
                throw new UsernameNotFoundException("Utilisateur " + login + " non existant dans la base de données");
            }
            else {
                UserDetails userDetails = User.builder()
                        .username(utilisateur.getEmail())
                        .password(utilisateur.getMotDePasse())
                        .roles(utilisateur.getRoles().toArray(new String[0]))
                        .build();
                return userDetails;
            }
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
