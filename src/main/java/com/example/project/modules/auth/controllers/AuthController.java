public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<String> register(User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<String> login(String username, String password) {
        String token = authService.login(username, password);
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<String> logout(String token) {
        authService.logout(token);
        return ResponseEntity.ok("User logged out successfully");
    }
}