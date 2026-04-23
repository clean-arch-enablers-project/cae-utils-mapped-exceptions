
from cae_mapped_exceptions.mapped_exception import MappedException

class InputMappedException(MappedException):

    def __init__(self, brief_public_message: str | None = None, details: str | None = None, original_exception: Exception  | None = None):
        super().__init__(brief_public_message, details, original_exception)