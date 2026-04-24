from cae_mapped_exceptions.mapped_exception import MappedException


class NotAuthorizedMappedException(MappedException):

    def __init__(
            self,
            brief_public_message: str | None = None,
            details: str | None = None,
            original: Exception | None = None
    ):
        super().__init__(brief_public_message, details, original)
