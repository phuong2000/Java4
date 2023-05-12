
package com.websiteshop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import com.websiteshop.dao.AccountDAO;
import com.websiteshop.entity.Account;
import com.websiteshop.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO adao;

    @Autowired
    AccountService accountService;

    @Override
    public Page<Account> findByFullnameContaining(String fullname, Pageable pageable) {
        return adao.findByFullnameContaining(fullname, pageable);
    }

    @Override
    public Optional<Account> findById(String id) {
        return adao.findById(id);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return adao.save(entity);
    }

    @Override
    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return adao.findOne(example);
    }

    @Override
    public List<Account> findAll() {
        return adao.findAll();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return adao.findAll(pageable);
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return adao.findAll(sort);
    }

    @Override
    public List<Account> findAllById(Iterable<String> ids) {
        return adao.findAllById(ids);
    }

    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return adao.saveAll(entities);
    }

    @Override
    public void flush() {
        adao.flush();
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return adao.saveAndFlush(entity);
    }

    @Override
    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return adao.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return adao.findAll(example, pageable);
    }

    @Override
    public void deleteInBatch(Iterable<Account> entities) {
        adao.deleteInBatch(entities);
    }

    @Override
    public boolean existsById(String id) {
        return adao.existsById(id);
    }

    @Override
    public <S extends Account> long count(Example<S> example) {
        return adao.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<Account> entities) {
        adao.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Account> boolean exists(Example<S> example) {
        return adao.exists(example);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> ids) {
        adao.deleteAllByIdInBatch(ids);
    }

    @Override
    public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return adao.findBy(example, queryFunction);
    }

    @Override
    public long count() {
        return adao.count();
    }

    @Override
    public void deleteAllInBatch() {
        adao.deleteAllInBatch();
    }

    @Override
    public void deleteById(String id) {
        adao.deleteById(id);
    }

    @Override
    public Account getOne(String id) {
        return adao.getOne(id);
    }

    @Override
    public void delete(Account entity) {
        adao.delete(entity);
    }

    @Override
    public Account getById(String id) {
        return adao.getById(id);
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        adao.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
        adao.deleteAll(entities);
    }

    @Override
    public Account getReferenceById(String id) {
        return adao.getReferenceById(id);
    }

    @Override
    public void deleteAll() {
        adao.deleteAll();
    }

    @Override
    public <S extends Account> List<S> findAll(Example<S> example) {
        return adao.findAll(example);
    }

    @Override
    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return adao.findAll(example, sort);
    }

    @Override
    public List<Account> findByUsernameContaining(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Account> getAdministrators() {
        return adao.getAdministratiors();
    }

    // ********************************
    // login with outside
    @Override
    public void loginFormOAuth2(OAuth2AuthenticationToken oauth2) {
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());

        UserDetails user = User.withUsername(email)
                .password(password).roles("CUST").build();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    // reset password

    public void updateResetPasswordToken(String token, String email) {
        Account account = adao.findByEmail(email);

        if (account != null) {
            account.setReset_password(token);
            adao.save(account);
        }
    }

    public Account get(String resetPasswordToken) {
        return adao.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(Account account, String newPassword) {
        // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // String encodepassword = passwordEncoder.encode(newPassword);

        account.setPassword(newPassword);
        account.setReset_password(null);

        adao.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return adao.findByUsername(username);
    }

}
