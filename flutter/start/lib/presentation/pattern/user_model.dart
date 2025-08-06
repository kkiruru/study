class User {
  final String id;
  final String name;
  final String email;
  final String phone;

  User({
    required this.id,
    required this.name,
    required this.email,
    required this.phone,
  });

  User copyWith({String? id, String? name, String? email, String? phone}) {
    return User(
      id: id ?? this.id,
      name: name ?? this.name,
      email: email ?? this.email,
      phone: phone ?? this.phone,
    );
  }

  @override
  String toString() {
    return 'User(id: $id, name: $name, email: $email, phone: $phone)';
  }
}
