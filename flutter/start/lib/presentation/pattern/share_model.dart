class Share {
  final String title;
  final String content;
  final String url;
  final DateTime createdAt;

  Share({
    required this.title,
    required this.content,
    required this.url,
    required this.createdAt,
  });

  Share copyWith({
    String? title,
    String? content,
    String? url,
    DateTime? createdAt,
  }) {
    return Share(
      title: title ?? this.title,
      content: content ?? this.content,
      url: url ?? this.url,
      createdAt: createdAt ?? this.createdAt,
    );
  }

  @override
  String toString() {
    return 'Share(title: $title, content: $content, url: $url, createdAt: $createdAt)';
  }
}
